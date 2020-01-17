package com.wispapp.gcpsoftwaretestapp.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wispapp.gcpsoftwaretestapp.core.di.ViewModelKey
import com.wispapp.gcpsoftwaretestapp.core.extensions.ViewModelClassMap
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemModel
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse
import com.wispapp.gcpsoftwaretestapp.core.model.repository.DataSource
import com.wispapp.gcpsoftwaretestapp.ui.main.MainActivity
import com.wispapp.gcpsoftwaretestapp.ui.viewmodels.MenuViewModel
import com.wispapp.gcpsoftwaretestapp.ui.viewmodels.MenuViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule.ProvideViewModel::class,
        ViewModelModule.ProvideViewModelAbstractionMap::class
    ]
)
abstract class ViewModelModule {

    @Module
    class InjectViewModel {
        @Provides
        fun menuViewModel(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ): MenuViewModel =
            ViewModelProviders.of(target, factory).get(MenuViewModelImpl::class.java)
    }

    @Module
    class ProvideViewModelAbstractionMap {
        @Provides
        fun viewModelClassMap(): ViewModelClassMap =
            mapOf(MenuViewModel::class.java to MenuViewModelImpl::class.java)
    }

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(MenuViewModel::class)
        fun menuViewModel(dataSource: DataSource<MenuResponse, List<MenuItemModel>>): ViewModel =
            MenuViewModelImpl(dataSource)
    }
}