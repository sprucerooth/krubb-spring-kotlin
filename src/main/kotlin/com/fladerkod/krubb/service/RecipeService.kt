package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.service.exception.DocumentNotFoundException
import org.springframework.stereotype.Service

@Service
class RecipeService(val recipeDataService: RecipeDataService) {
    fun getAll() = recipeDataService.getAll()
    fun getById(id: String): Recipe = recipeDataService.getById(id).orElseThrow { DocumentNotFoundException("Not found!!!") }
    fun save(recipe: Recipe): String = recipeDataService.insertOne(recipe)
    fun update(id: String, incomingRecipeChanges: Recipe): String {
        val existingRecipe: Recipe = getById(id)
        val updatedRecipe = existingRecipe.copy(name = incomingRecipeChanges.name, description = incomingRecipeChanges.description)
        return recipeDataService.saveOne(updatedRecipe)
    }

    fun deleteById(id: String) = recipeDataService.deleteById(id)
}