package com.example.recipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.utils.collectLifeCycleFlow
import com.example.recipes.utils.visibleIf
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity()/*, AndroidScopeComponent*/ {
//    override val scope: Scope by activityScope()

    private val viewModel: RecipesViewModel by lazy { getViewModel() }
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
        listeners()
        viewModel.homeState.observe(this) { homeState ->
            recipesAdapter.submitList(homeState.recipes)
            binding.progressBar visibleIf homeState.loading
            binding.getRecipesBtn visibleIf homeState.recipes.isEmpty()
            binding.rv visibleIf homeState.recipes.isEmpty().not()
        }
        collectLifeCycleFlow(viewModel.errorState) {
            Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun listeners() {
        binding.getRecipesBtn.setOnClickListener {
            viewModel.senEvent(RecipesEvent.GetRecipes)
        }
    }

    private fun initUi() {
        recipesAdapter = RecipesAdapter()
        val layoutManager = GridLayoutManager(this, 2)
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = recipesAdapter
    }
}