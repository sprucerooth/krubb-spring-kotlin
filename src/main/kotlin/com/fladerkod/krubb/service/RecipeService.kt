package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.service.exception.DocumentNotFoundException
import org.springframework.stereotype.Service

@Service
class RecipeService(val recipeDataService: DataService<Recipe>) {
    fun getAll() = recipeDataService.getAll()

    fun getById(id: String): Recipe = recipeDataService.getById(id).orElseThrow { DocumentNotFoundException("Recipe with id $id was not found") }

    fun save(recipe: Recipe): String = recipeDataService.insertOne(recipe)

    fun deleteById(id: String) = recipeDataService.deleteById(id)

    fun update(id: String, incomingUpdatedRecipe: Recipe): String {
        val existingRecipe: Recipe = getById(id)
        val updatedRecipe = existingRecipe.copy(name = incomingUpdatedRecipe.name, description = incomingUpdatedRecipe.description)
        return recipeDataService.saveOne(updatedRecipe)
    }
}

@Service
class RecipeImageService(val recipeImageDataService: ManyToOneDataService<RecipeImage, Recipe>) {

    fun getById(id: String): RecipeImage = recipeImageDataService.getById(id).orElseThrow { DocumentNotFoundException("Image with id $id was not found") }

    fun save(recipeImage: RecipeImage): String = recipeImageDataService.insertOne(recipeImage)

    fun deleteById(id: String) = recipeImageDataService.deleteById(id)

    fun getAllByRecipeId(recipe: Recipe): List<RecipeImage> = recipeImageDataService.getAllByForeignKey(recipe)
}