package com.fladerkod.krubb.service

interface ManyToOneDataService<R, T> : DataService<R> {

    fun getAllByForeignKey(dto: T): List<R>
}