package com.wispapp.gcpsoftwaretestapp.core.model.mappers

import com.wispapp.gcpsoftwaretestapp.core.common.Mapper
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.Function
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemModel
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemResponse
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse

class MenuItemMapper : Mapper<MenuResponse, List<MenuItemModel>> {

    companion object {

        private const val FUNCTION_TEXT = "function_text"
        private const val FUNCTION_IMAGE = "function_image"
        private const val FUNCTION_URL = "function_url"
    }

    override fun mapFrom(source: MenuResponse): List<MenuItemModel> =
        source.menu.map { mapMenuItemFromResponse(it) }

    private fun mapMenuItemFromResponse(item: MenuItemResponse): MenuItemModel =
        MenuItemModel(
            name = item.name,
            function = getFunction(item.function),
            param = item.param
        )

    private fun getFunction(function: String): Function =
        when (function) {
            FUNCTION_TEXT -> Function.TEXT
            FUNCTION_IMAGE -> Function.IMAGE
            FUNCTION_URL -> Function.URL
            else -> throw IllegalArgumentException("Wrong function type")
        }
}