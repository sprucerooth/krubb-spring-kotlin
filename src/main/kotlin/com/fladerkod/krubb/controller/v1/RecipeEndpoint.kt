package com.fladerkod.krubb.controller.v1

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.service.RecipeService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/rest/v1/recipes")
class RecipeEndpoint(val recipeService: RecipeService) {

    @GetMapping
    fun findAll() = recipeService.getAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Recipe = recipeService.getById(id)

    @PostMapping
    fun save(@RequestBody recipe: Recipe): String = recipeService.save(recipe)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody recipe: Recipe): String = recipeService.update(id, recipe)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = recipeService.deleteById(id);
}