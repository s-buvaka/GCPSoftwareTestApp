package com.wispapp.gcpsoftwaretestapp.core.model.pojo

data class MenuItemModel(
    val name: String,
    val function: Function,
    val param: String
)

enum class Function { TEXT, IMAGE, URL }