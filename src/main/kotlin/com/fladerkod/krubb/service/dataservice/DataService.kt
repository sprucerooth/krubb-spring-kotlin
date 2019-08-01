package com.fladerkod.krubb.service.dataservice

interface DataService<R> {

    fun getAll(): List<R>
    fun getById(id: String): R
    fun insertOne(dto: R): String
    fun saveOne(dto: R): String
    fun deleteById(id: String)
}