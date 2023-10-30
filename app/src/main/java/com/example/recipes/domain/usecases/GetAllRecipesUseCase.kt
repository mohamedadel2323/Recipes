package com.example.recipes.domain.usecases

import com.example.recipes.domain.repository.RepositoryInterface

class GetAllRecipesUseCase(private val repository: RepositoryInterface) {
    suspend operator fun <T> invoke() = repository.getAllRecipes<T>()
}