package com.wispapp.gcpsoftwaretestapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

abstract class BaseViewModel : ViewModel() {

    protected val backgroundExecutor: Executor by lazy { Executors.newCachedThreadPool() }

    val isDataLoading = MutableLiveData<Boolean>()
    val exception = MutableLiveData<String>()

    protected fun showLoader() = setLoading(true)

    protected fun hideLoader() = setLoading(false)

    protected fun showError(errorMessage: String) {
        exception.postValue(errorMessage)
    }

    private fun setLoading(isLoading: Boolean) {
        isDataLoading.postValue(isLoading)
    }
}