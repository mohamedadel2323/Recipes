package com.example.recipes.data.repository

import com.example.recipes.utils.Response
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun <T> getAllRecipes(): Response<T>
}