package com.fladerkod.krubb.persistence

import com.fladerkod.krubb.dto.Ingredient
import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

interface MongoEntity<R> {
    fun toDto(): R
}

@Document("recipe")
data class RecipeEntity(@Id val id: String, val name: String, val description: String?, val ingredients: Array<Ingredient>?) : MongoEntity<Recipe> {
    constructor(recipe: Recipe) : this(recipe.id
            ?: ObjectId().toString(), recipe.name, recipe.description, recipe.ingredients)

    override fun toDto(): Recipe {
        return Recipe(id, name, description, null, ingredients ?: emptyArray())
    }
}

@Document("recipe-image")
data class RecipeImageEntity(@Id val id: String, val recipeId: String, val image: ByteArray) : MongoEntity<RecipeImage> {
    constructor(recipeImage: RecipeImage) : this(recipeImage.id
            ?: ObjectId().toString(), recipeImage.recipeId, recipeImage.base64Image.toByteArray())

    override fun toDto(): RecipeImage {
        return RecipeImage(id, recipeId, String(image))
    }
}

@Document("recipe-thumbnail")
data class RecipeThumbnailEntity(@Id val id: String, val recipeId: String, val image: ByteArray) : MongoEntity<RecipeImage> {
    constructor(recipeThumbnail: RecipeImage) : this(recipeThumbnail.id
            ?: ObjectId().toString(), recipeThumbnail.recipeId, recipeThumbnail.base64Image.toByteArray())

    override fun toDto(): RecipeImage {
        return RecipeImage(id, recipeId, String(image))
    }
}