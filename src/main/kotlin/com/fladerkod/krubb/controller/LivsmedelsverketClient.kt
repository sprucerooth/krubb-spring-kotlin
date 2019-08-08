package com.fladerkod.krubb.controller

import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
@CrossOrigin
@RequestMapping("/rest/external/ingredients")
class LivsmedelsverketClient {

    @Cacheable("ingredient")
    @GetMapping(params = ["search-phrase"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun searchIngredient(@RequestParam("search-phrase") searchPhrase: String): String? {

        val uri = "https://www7.slv.se/SokNaringsinnehall/Home/HamtaLivsmedelTillAutoComplete?sokOrd=$searchPhrase&soktyp=1&_=1564687838842"

        val restTemplate = RestTemplate()
        return restTemplate.getForObject(uri, String::class.java)
    }
}