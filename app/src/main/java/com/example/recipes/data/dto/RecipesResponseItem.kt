package com.example.recipes.data.dto

data class RecipesResponseItem(
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