package com.wispapp.gcpsoftwaretestapp.core.model.pojo

data class MenuResponse(val menu: List<MenuItemResponse>)

data class MenuItemResponse(
	val name: String,
	val function: String,
	val param: String
)
