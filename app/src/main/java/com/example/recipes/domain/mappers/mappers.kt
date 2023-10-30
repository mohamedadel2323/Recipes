package com.example.recipes.domain.mappers

import com.example.recipes.data.dto.RecipesResponseItem
import com.example.recipes.domain.models.RecipeDomainModel


fun RecipesResponseItem.toDomainModel() = RecipeDomainModel(
    id = this.id ?: "",
    name = this.name ?: "",
    headline = this.headline ?: "",
    description = this.description ?: "",
    difficulty = this.difficulty ?: 0,
    time = this.time ?: "",
    calories = this.calories ?: "",
    carbs = this.carbs ?: "",
    fats = this.fats ?: "",
    proteins = this.proteins ?: "",
    image = this.image ?: "",
    thumb = this.thumb ?: ""
)