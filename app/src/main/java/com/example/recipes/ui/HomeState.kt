package com.example.recipes.ui

import com.example.recipes.data.dto.RecipesResponse
import com.example.recipes.data.dto.RecipesResponseItem

data class HomeState(
    val loading: Boolean = true,
    val recipes: List<RecipesResponseItem> = listOf(),
    val error: String = ""
)
