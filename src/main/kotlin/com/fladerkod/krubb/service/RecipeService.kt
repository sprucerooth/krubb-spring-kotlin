package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.service.dataservice.DataService
import com.fladerkod.krubb.service.dataservice.ManyToOneDataService
import com.fladerkod.krubb.service.dataservice.OneToOneDataService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecipeService(val recipeDataService: DataService<Recipe>, val recipeImageDataService: ManyToOneDataService<RecipeImage, Recipe>, val thumbnailDataService: OneToOneDataService<RecipeImage, Recipe>) {
    fun getAll(): List<Recipe> {
        val recipes: List<Recipe> = recipeDataService.getAll()
        val recipeThumbnails: Map<String, RecipeImage> = thumbnailDataService.findByFk(recipes).map { it.recipeId to it }.toMap()
        return recipes.map { it.copy(id = it.id, name = it.name, description = it.description, base64Thumbnail = recipeThumbnails[it.id]?.base64Image) }
    }

    fun getById(id: String) = recipeDataService.getById(id)

    @Transactional
    fun save(recipe: Recipe): String {
        val recipeId: String = recipeDataService.insertOne(recipe)
        if (recipe.base64Thumbnail != null) thumbnailDataService.insertOne(RecipeImage(null, recipeId, recipe.base64Thumbnail))
        return recipeId
    }

    @Transactional
    fun deleteById(id: String) {
        recipeDataService.getById(id)
        recipeDataService.deleteById(id)
        recipeImageDataService.deleteById(id)
    }

    @Transactional
    fun update(id: String, incomingUpdatedRecipe: Recipe): String {
        val existingRecipe = recipeDataService.getById(id)
        val updatedRecipe = existingRecipe.copy(name = incomingUpdatedRecipe.name, description = incomingUpdatedRecipe.description)
        recipeDataService.saveOne(updatedRecipe)
        if (incomingUpdatedRecipe.base64Thumbnail != null) thumbnailDataService.insertOne(RecipeImage(null, id, incomingUpdatedRecipe.base64Thumbnail))
        return id
    }
}