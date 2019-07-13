package com.fladerkod.krubb.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeDao : MongoRepository<RecipeEntity, String>

@Repository
interface RecipeImageDao : MongoRepository<RecipeImageEntity, String> {
    fun findAllByRecipeId(recipeId: String): List<RecipeImageEntity>
}