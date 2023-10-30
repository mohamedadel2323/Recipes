package com.example.recipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipes.MyApplication
import com.example.recipes.R
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.utils.collectLifeCycleFlow
import com.example.recipes.utils.visibleIf
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by lazy {
        ViewModelProvider(this, (application as MyApplication).dependenciesProvider.getRecipesViewModelFactory())[RecipesViewModel::class.java]
    }
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
        listeners()
        viewModel.homeState.observe(this) { homeState ->
            Log.e("MainActivity", "Triggered")
            Log.e("MainActivity", homeState.recipes.toString())

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