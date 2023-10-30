package com.example.recipes.domain.models

data class RecipeDomainModel(
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val difficulty: Int,
    val time: String,
    val calories: String,
    val carbs: String,
    val fats: String,
    val proteins: String,
    val image: String,
    val thumb: String
)