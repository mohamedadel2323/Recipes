package com.example.recipes.data.remote

import com.example.recipes.utils.Response

interface RemoteSource {
    suspend fun <T> getAllRecipes(): Response<T>
}