package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.service.dataservice.DataService
import com.fladerkod.krubb.service.dataservice.ManyToOneDataService
import org.springframework.stereotype.Service

@Service
class RecipeImageService(val recipeImageDataService: ManyToOneDataService<RecipeImage, Recipe>, val recipeDataService: DataService<Recipe>) {

    fun getById(id: String) = recipeImageDataService.getById(id)

    fun save(recipeId: String, images: List<String>) {
        recipeDataService.getById(recipeId)
        val recipeImages = images.map { imageString -> RecipeImage(null, recipeId, imageString) }
        recipeImageDataService.insert(recipeImages)
    }

    fun deleteById(id: List<String>) = recipeImageDataService.deleteById(id)

    fun getAllByRecipeId(recipeId: String): List<RecipeImage> {
        val recipe = recipeDataService.getById(recipeId)
        return recipeImageDataService.getAllByForeignKey(recipe)
    }
}