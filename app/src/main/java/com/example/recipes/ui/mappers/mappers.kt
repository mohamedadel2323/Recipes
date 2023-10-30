package com.example.recipes.ui.mappers

import com.example.recipes.domain.models.RecipeDomainModel
import com.example.recipes.ui.models.RecipeUiModel

fun RecipeDomainModel.toUiModel() = RecipeUiModel(recipeName = this.name, recipeImage = this.thumb)