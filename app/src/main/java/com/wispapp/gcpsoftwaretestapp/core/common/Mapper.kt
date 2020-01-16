package com.wispapp.gcpsoftwaretestapp.core.common

interface Mapper<E, T> {

    fun mapFrom(source: E): T
}