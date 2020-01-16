package com.wispapp.gcpsoftwaretestapp.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

typealias ViewModelClassMap = Map<Class<out ViewModel>, @JvmSuppressWildcards Class<out ViewModel>>

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> ViewModelClassMap.getImplClass(clazz: Class<out ViewModel>): Class<T> =
    requireNotNull(get(clazz)) as Class<T>

inline fun <reified T : ViewModel> Fragment.sharedViewModel(
    factory: ViewModelProvider.Factory,
    classMap: ViewModelClassMap
): T = ViewModelProviders.of(requireActivity(), factory)
    .get(classMap.getImplClass<T>(T::class.java))