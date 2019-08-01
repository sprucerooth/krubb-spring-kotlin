package com.fladerkod.krubb.controller.v1

import com.fladerkod.krubb.service.RecipeImageService
import com.fladerkod.krubb.service.RecipeService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/rest/v1/recipes/{recipeId}/images")
class RecipeImageEndpoint(val recipeImageService: RecipeImageService, val recipeService: RecipeService) {

    @GetMapping()
    fun findAllByRecipeId(@PathVariable recipeId: String) = recipeImageService.getAllByRecipeId(recipeId)

    @PostMapping()
    fun save(@PathVariable recipeId: String, @RequestBody images: List<String>) = recipeImageService.save(recipeId, images)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: List<String>) = recipeImageService.deleteById(id)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = recipeImageService.getById(id)
}