package com.fladerkod.krubb.persistence.dataservice

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.persistence.RecipeThumbailDao
import com.fladerkod.krubb.persistence.RecipeThumbnailEntity
import com.fladerkod.krubb.service.dataservice.OneToOneDataService
import org.springframework.stereotype.Service

@Service
class RecipeThumbnailMongoService(val recipeThumbnailDao: RecipeThumbailDao) : OneToOneDataService<RecipeImage, Recipe> {
    override fun insertOne(dto: RecipeImage) {
        recipeThumbnailDao.insert(RecipeThumbnailEntity(dto))
    }

    override fun findByFk(dto: List<Recipe>): List<RecipeImage> {

        val recipeIds: List<String> = dto.mapNotNull(Recipe::id)

        return recipeThumbnailDao.findByRecipeIdIn(recipeIds).map(RecipeThumbnailEntity::toDto)
    }
}