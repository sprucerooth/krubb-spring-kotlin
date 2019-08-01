package com.fladerkod.krubb.persistence.dataservice

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.persistence.RecipeDao
import com.fladerkod.krubb.persistence.RecipeEntity
import com.fladerkod.krubb.service.dataservice.DataService
import com.fladerkod.krubb.service.exception.DocumentNotFoundException
import org.springframework.stereotype.Service

@Service
class RecipeMongoService(val recipeDao: RecipeDao) : DataService<Recipe> {
    override fun deleteById(id: String) = recipeDao.deleteById(id)

    override fun getById(id: String): Recipe =
            recipeDao.findById(id).map(RecipeEntity::toDto).orElseThrow { DocumentNotFoundException("Recipe with id $id was not found") }

    override fun insertOne(dto: Recipe) = recipeDao.insert(RecipeEntity(dto)).id

    override fun getAll(): List<Recipe> = recipeDao.findAll().map(RecipeEntity::toDto)

    override fun saveOne(dto: Recipe) = recipeDao.save(RecipeEntity(dto)).id
}