package com.example.recipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipes.R
import com.example.recipes.data.remote.RecipesClient
import com.example.recipes.data.repository.Repository
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.utils.visibleIf


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipesViewModel
    private lateinit var viewModelFactory: RecipesViewModelFactory
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUi()

        viewModel.homeState.observe(this) { homeState ->
            recipesAdapter.submitList(homeState.recipes)
            if (homeState.error.isNotEmpty())
                Log.d("Recipes", homeState.error)
            binding.progressBar visibleIf homeState.loading
        }
    }

    private fun initUi() {
        viewModelFactory = RecipesViewModelFactory(Repository(RecipesClient))
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]

        recipesAdapter = RecipesAdapter()
        val layoutManager = GridLayoutManager(this, 2)
        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = recipesAdapter
    }
}