package com.wispapp.gcpsoftwaretestapp.core.model.remote

interface BaseUrlProvider {

    fun getBaseUrl(): String
}

class BaseUrlProviderImpl : BaseUrlProvider {

    companion object {
        private const val BASE_URL = "https://www.dropbox.com/s/"
    }

    override fun getBaseUrl(): String = BASE_URL
}