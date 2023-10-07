package com.example.recipes.data.remote

import com.example.recipes.utils.Constants.BASE_URL
import com.example.recipes.utils.Response
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object RecipesClient : RemoteSource {

    private val recipesService: RecipesService

    init {
        val retrofitInstance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        recipesService = retrofitInstance.create(RecipesService::class.java)
    }

    override suspend fun <T> getAllRecipes(): Flow<Response<T>> {
        return flowOf(
            try {
                Response.Success(recipesService.getRecipes() as T)
            } catch (e: Exception) {
                Response.Failure(e.message ?: "Unknown Error")
            }
        )
    }

}