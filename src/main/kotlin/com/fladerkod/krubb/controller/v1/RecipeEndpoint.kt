package com.fladerkod.krubb.controller.v1

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.service.RecipeService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/rest/v1/recipes")
class RecipeEndpoint(val recipeService: RecipeService) {

    @GetMapping
    fun findAll() = recipeService.getAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = recipeService.getById(id)

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody recipe: Recipe): String {
        val recipeId = recipeService.save(recipe)
        return "{\"id\":\"$recipeId\"}"
    }

    @PutMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: String, @RequestBody recipe: Recipe): String {
        val recipeId = recipeService.update(id, recipe)
        return "{\"id\":\"$recipeId\"}"
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = recipeService.deleteById(id);
}