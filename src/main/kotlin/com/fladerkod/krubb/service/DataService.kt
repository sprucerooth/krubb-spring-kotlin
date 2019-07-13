package com.fladerkod.krubb.service

import java.util.*

interface DataService<R> {

    fun getAll(): List<R>
    fun getById(id: String): Optional<R>
    fun insertOne(dto: R): String
    fun saveOne(dto: R): String
    fun deleteById(id: String)
}