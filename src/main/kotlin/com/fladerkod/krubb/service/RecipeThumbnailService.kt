package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.Recipe
import com.fladerkod.krubb.dto.RecipeImage
import com.fladerkod.krubb.service.dataservice.OneToOneDataService
import org.springframework.stereotype.Service

@Service
class RecipeThumbnailService(val recipeThumbnailDataService: OneToOneDataService<RecipeImage, Recipe>) {
}