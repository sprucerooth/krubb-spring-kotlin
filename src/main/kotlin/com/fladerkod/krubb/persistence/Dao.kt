package com.fladerkod.krubb.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface RecipeDao : MongoRepository<RecipeEntity, String>