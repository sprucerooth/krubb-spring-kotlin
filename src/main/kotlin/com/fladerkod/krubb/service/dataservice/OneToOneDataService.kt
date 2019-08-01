package com.fladerkod.krubb.service.dataservice

interface OneToOneDataService<R, T> {
    fun insertOne(dto: R)
    fun findByFk(dto: List<T>): List<R>
}