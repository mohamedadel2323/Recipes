package com.example.recipes.ui

import com.example.recipes.ui.models.RecipeUiModel

sealed class HomeState {
    data class Display(
        val loading: Boolean = false,
        val recipes: List<RecipeUiModel> = listOf(),
    ) : HomeState()

    data class Error(
        val error: String = ""
    )
}

