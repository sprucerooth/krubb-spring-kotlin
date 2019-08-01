package com.fladerkod.krubb.persistence.dataservice

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.persistence.RecipeImageDao
import com.fladerkod.krubb.persistence.RecipeImageEntity
import com.fladerkod.krubb.service.dataservice.ManyToOneDataService
import com.fladerkod.krubb.service.exception.DocumentNotFoundException
import org.springframework.stereotype.Service

@Service
class ImageMongoService(val recipeImageDao: RecipeImageDao) : ManyToOneDataService<RecipeImage, Recipe> {
    override fun insert(dto: List<RecipeImage>) {
        recipeImageDao.insert(dto.map(::RecipeImageEntity))
    }

    override fun getAllByForeignKey(dto: Recipe): List<RecipeImage> {
        if (dto.id != null) {
            return recipeImageDao.findAllByRecipeId(dto.id).map(RecipeImageEntity::toDto)
        } else {
            throw NullPointerException("Id of recipe is null!")
        }
    }

    override fun getAll(): List<RecipeImage> = recipeImageDao.findAll().map(RecipeImageEntity::toDto)

    override fun getById(id: String): RecipeImage = recipeImageDao.findById(id).map(RecipeImageEntity::toDto).orElseThrow { DocumentNotFoundException("Image with id $id was not found") }

    override fun insertOne(dto: RecipeImage): String = recipeImageDao.insert(RecipeImageEntity(dto)).id

    override fun saveOne(dto: RecipeImage): String = recipeImageDao.save(RecipeImageEntity(dto)).id

    override fun deleteById(id: String) = recipeImageDao.deleteById(id)

    override fun deleteById(ids: List<String>) = recipeImageDao.deleteAllByIdIn(ids)
}
