package com.wispapp.gcpsoftwaretestapp.core.di.modules

import com.wispapp.gcpsoftwaretestapp.core.common.Mapper
import com.wispapp.gcpsoftwaretestapp.core.model.mappers.MenuItemMapper
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemModel
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse
import com.wispapp.gcpsoftwaretestapp.core.model.remote.RemoteProvider
import com.wispapp.gcpsoftwaretestapp.core.model.repository.DataSource
import com.wispapp.gcpsoftwaretestapp.core.model.repository.DataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideMenuDataSource(
        remoteProvider: RemoteProvider<MenuResponse>,
        mapper: Mapper<MenuResponse, List<MenuItemModel>>
    ): DataSource<MenuResponse, List<MenuItemModel>> = DataSourceImpl(remoteProvider, mapper)

    @Provides
    fun provideMenuItemMapper(): Mapper<MenuResponse, List<MenuItemModel>> = MenuItemMapper()
}