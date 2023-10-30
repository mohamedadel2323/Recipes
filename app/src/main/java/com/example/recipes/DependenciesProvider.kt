package com.example.recipes

import com.example.recipes.data.remote.RecipesClient
import com.example.recipes.data.remote.RecipesService
import com.example.recipes.data.repository.Repository
import com.example.recipes.domain.usecases.GetAllRecipesUseCase
import com.example.recipes.ui.RecipesViewModelFactory
import com.example.recipes.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependenciesProvider {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
    private val remoteSource = RecipesClient(retrofit.create(RecipesService::class.java))
    private val repository = Repository(remoteSource)
    private val getAllRecipesUseCase = GetAllRecipesUseCase(repository)
    private val recipesViewModelFactory = RecipesViewModelFactory(getAllRecipesUseCase)
    fun getRecipesViewModelFactory() = recipesViewModelFactory
}