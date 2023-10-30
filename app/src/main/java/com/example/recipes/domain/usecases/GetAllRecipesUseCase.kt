package com.example.recipes.domain.usecases

import com.example.recipes.domain.repository.Repository

class GetAllRecipesUseCase(private val repository: Repository) {
    suspend operator fun <T> invoke() = repository.getAllRecipes<T>()
}