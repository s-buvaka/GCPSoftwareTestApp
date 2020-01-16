package com.wispapp.gcpsoftwaretestapp.core.model.remote

import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse

class MenuRemoteProvider(private val baseUrl: String) : BaseRemoteProvider<List<MenuResponse>>() {

    companion object {
        private const val URL_GET_MENU = "fk3d5kg6cptkpr6/menu.json?dl=1"
    }

    override fun get(): Result<List<MenuResponse>> {
        val url = baseUrl + URL_GET_MENU
        return getResult(url)
    }
}