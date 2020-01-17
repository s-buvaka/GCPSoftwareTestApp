package com.wispapp.gcpsoftwaretestapp.core.di.modules

import com.wispapp.gcpsoftwaretestapp.core.di.scope.AppScope
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse
import com.wispapp.gcpsoftwaretestapp.core.model.remote.BaseUrlProvider
import com.wispapp.gcpsoftwaretestapp.core.model.remote.BaseUrlProviderImpl
import com.wispapp.gcpsoftwaretestapp.core.model.remote.MenuRemoteProvider
import com.wispapp.gcpsoftwaretestapp.core.model.remote.RemoteProvider
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @AppScope
    @Provides
    fun provideMenuRemoteProvider(urlProvider: BaseUrlProvider): RemoteProvider<MenuResponse> =
        MenuRemoteProvider(urlProvider.getBaseUrl())

    @Provides
    fun provideBaseUrl(): BaseUrlProvider = BaseUrlProviderImpl()
}