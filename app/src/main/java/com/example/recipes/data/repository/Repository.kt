package com.example.recipes.data.repository

import com.example.recipes.data.dto.RecipesResponse
import com.example.recipes.data.remote.RemoteSource
import com.example.recipes.domain.mappers.toDomainModel
import com.example.recipes.domain.repository.RepositoryInterface
import com.example.recipes.utils.Response
import java.lang.Exception

class Repository(private val recipesClient: RemoteSource) : RepositoryInterface {
    override suspend fun <T> getAllRecipes(): Response<T> =
        try {
            Response.Success((recipesClient.getAllRecipes<RecipesResponse>().data?.map { it.toDomainModel() }) as T)
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Unknown Error")
        }
}