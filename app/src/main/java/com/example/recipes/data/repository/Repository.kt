package com.example.recipes.data.repository

import com.example.recipes.data.remote.RemoteSource
import com.example.recipes.utils.Response
import kotlinx.coroutines.flow.Flow

class Repository(private val recipesClient: RemoteSource) : RepositoryInterface {

    override suspend fun <T> getAllRecipes(): Flow<Response<T>> =
        recipesClient.getAllRecipes()

}