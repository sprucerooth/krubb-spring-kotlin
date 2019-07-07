package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import java.util.*

interface RecipeDataService {

    fun getAll(): List<Recipe>
    fun getById(id: String): Optional<Recipe>
    fun insertOne(recipe: Recipe): String
    fun saveOne(recipe: Recipe): String
    fun deleteById(id: String)
}