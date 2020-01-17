package com.wispapp.gcpsoftwaretestapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemModel
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse
import com.wispapp.gcpsoftwaretestapp.core.model.remote.Result
import com.wispapp.gcpsoftwaretestapp.core.model.repository.DataSource

abstract class MenuViewModel : BaseViewModel() {

    abstract val menuLiveData: LiveData<List<MenuItemModel>>

    abstract fun getMenu()
}

class MenuViewModelImpl(private val dataSource: DataSource<MenuResponse, List<MenuItemModel>>) :
    MenuViewModel() {

    override val menuLiveData = MutableLiveData<List<MenuItemModel>>()

    override fun getMenu() {
        showLoader()
        backgroundExecutor.execute {
            when (val result = dataSource.get()) {
                is Result.Success -> menuLiveData.postValue(result.data)
                is Result.Error -> showError(result.exception.message ?: "Error receiving menu")
            }
            hideLoader()
        }
    }
}