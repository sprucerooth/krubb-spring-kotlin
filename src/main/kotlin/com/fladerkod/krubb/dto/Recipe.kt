package com.fladerkod.krubb.dto

data class Recipe(val id: String?, val name: String, val description: String)

data class RecipeImage(val id: String?, val recipeId: String, val base64Image: String)