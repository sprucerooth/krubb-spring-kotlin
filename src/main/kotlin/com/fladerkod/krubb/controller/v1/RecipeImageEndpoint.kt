package com.fladerkod.krubb.controller.v1

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.service.RecipeImageService
import com.fladerkod.krubb.service.RecipeService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/rest/v1/recipes/{recipeId}/images")
class RecipeImageEndpoint(val recipeImageService: RecipeImageService, val recipeService: RecipeService) {

    @GetMapping()
    fun findAllByRecipeId(@PathVariable recipeId: String): List<RecipeImage> {
        val recipe: Recipe = recipeService.getById(recipeId)
        return recipeImageService.getAllByRecipeId(recipe)
    }

    @PostMapping()
    fun save(@PathVariable recipeId: String, @RequestBody base64Image: String) {
        val recipe: Recipe = recipeService.getById(recipeId)
        recipeImageService.save(RecipeImage(null, recipeId, base64Image))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = recipeImageService.deleteById(id)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = recipeImageService.getById(id)
}