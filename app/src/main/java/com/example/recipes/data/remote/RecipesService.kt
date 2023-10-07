package com.example.recipes.data.remote

import com.example.recipes.data.dto.RecipesResponse
import retrofit2.http.GET


interface RecipesService {

    @GET("recipes.json")
    suspend fun getRecipes() : RecipesResponse
}