package com.example.recipes.domain.repository

import com.example.recipes.utils.Response

interface RepositoryInterface {
    suspend fun <T> getAllRecipes(): Response<T>
}