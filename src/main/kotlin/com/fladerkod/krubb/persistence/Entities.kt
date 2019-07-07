package com.fladerkod.krubb.persistence

import com.fladerkod.krubb.dto.Recipe
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("recipe")
data class RecipeEntity(@Id val id: String, val name: String, val description: String) {
    constructor(recipe: Recipe) : this(recipe.id ?: ObjectId().toString(), recipe.name, recipe.description)

    fun toDto(): Recipe {
        return Recipe(id, name, description)
    }

}
