package com.fladerkod.krubb.service.dataservice

interface ManyToOneDataService<R, T> : DataService<R> {

    fun getAllByForeignKey(dto: T): List<R>
    fun deleteById(ids: List<String>)
    fun insert(dto: List<R>)
}