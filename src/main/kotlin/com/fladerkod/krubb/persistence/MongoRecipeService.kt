package com.fladerkod.krubb.persistence

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.service.RecipeDataService
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class MongoRecipeService(val recipeDao: RecipeDao) : RecipeDataService {
    override fun deleteById(id: String) = recipeDao.deleteById(id)

    override fun getById(id: String): Optional<Recipe> = recipeDao.findById(id).map(RecipeEntity::toDto)

    override fun insertOne(recipe: Recipe) = recipeDao.insert(RecipeEntity(recipe)).id

    override fun getAll(): List<Recipe> = recipeDao.findAll().stream().map(RecipeEntity::toDto).collect(Collectors.toList())

    override fun saveOne(recipe: Recipe) = recipeDao.save(RecipeEntity(recipe)).id
}