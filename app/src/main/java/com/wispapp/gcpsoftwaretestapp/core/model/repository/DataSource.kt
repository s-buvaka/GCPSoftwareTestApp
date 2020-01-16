package com.wispapp.gcpsoftwaretestapp.core.model.repository

import com.wispapp.gcpsoftwaretestapp.core.common.Mapper
import com.wispapp.gcpsoftwaretestapp.core.model.remote.RemoteProvider
import com.wispapp.gcpsoftwaretestapp.core.model.remote.Result

interface DataSource<RESPONSE, MODEL> {

    fun get(): Result<MODEL>
}

class DataSourceImpl<RESPONSE, MODEL>(
    private val remoteProvider: RemoteProvider<RESPONSE>,
    private val mapper: Mapper<RESPONSE, MODEL>
) : DataSource<RESPONSE, MODEL> {

    override fun get(): Result<MODEL> = map(remoteProvider.get())

    private fun map(result: Result<RESPONSE>): Result<MODEL> =
        when (result) {
            is Result.Success -> Result.Success(mapper.mapFrom(result.data))
            is Result.Error -> result
        }
}