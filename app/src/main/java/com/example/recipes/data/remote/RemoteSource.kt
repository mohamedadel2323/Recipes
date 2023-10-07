package com.example.recipes.data.remote

import com.example.recipes.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    suspend fun <T> getAllRecipes(): Flow<Response<T>>
}