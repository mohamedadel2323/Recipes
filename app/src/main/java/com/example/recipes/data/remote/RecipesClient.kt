package com.example.recipes.data.remote

import com.example.recipes.utils.Response
import java.lang.Exception
import javax.inject.Inject

class RecipesClient @Inject constructor(private val recipesService: RecipesService) : RemoteSource {
    override suspend fun <T> getAllRecipes(): Response<T> {
        return try {
            Response.Success(recipesService.getRecipes() as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown Error")
        }
    }
}