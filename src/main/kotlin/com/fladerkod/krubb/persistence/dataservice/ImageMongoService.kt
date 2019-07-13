package com.fladerkod.krubb.persistence.dataservice

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.persistence.RecipeImageDao
import com.fladerkod.krubb.persistence.RecipeImageEntity
import com.fladerkod.krubb.service.ManyToOneDataService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageMongoService(val recipeImageDao: RecipeImageDao) : ManyToOneDataService<RecipeImage, Recipe> {
    override fun getAllByForeignKey(dto: Recipe): List<RecipeImage> {
        if (dto.id != null) {
            return recipeImageDao.findAllByRecipeId(dto.id).map(RecipeImageEntity::toDto)
        } else {
            throw NullPointerException("Id of recipe is null!")
        }
    }

    override fun getAll(): List<RecipeImage> = recipeImageDao.findAll().map(RecipeImageEntity::toDto)

    override fun getById(id: String): Optional<RecipeImage> = recipeImageDao.findById(id).map(RecipeImageEntity::toDto)

    override fun insertOne(dto: RecipeImage): String = recipeImageDao.insert(RecipeImageEntity(dto)).id

    override fun saveOne(dto: RecipeImage): String = recipeImageDao.save(RecipeImageEntity(dto)).id

    override fun deleteById(id: String) = recipeImageDao.deleteById(id)
}
