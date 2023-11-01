package com.example.recipes.domain.usecases

import com.example.recipes.domain.repository.RepositoryInterface
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(private val repository: RepositoryInterface) {
    suspend operator fun <T> invoke() = repository.getAllRecipes<T>()
}