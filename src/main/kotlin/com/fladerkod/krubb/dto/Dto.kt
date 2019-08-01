package com.fladerkod.krubb.dto

data class UserCredentials(val username: String, val password: String)

data class Recipe(val id: String?, val name: String, val description: String?, val base64Thumbnail: String? = null)

data class RecipeImage(val id: String?, val recipeId: String, val base64Image: String)