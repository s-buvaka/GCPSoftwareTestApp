package com.wispcoolwisp.dagger_viewmodel_sample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wispcoolwisp.dagger_viewmodel_sample.DataSource
import com.wispcoolwisp.dagger_viewmodel_sample.MainActivity
import com.wispcoolwisp.dagger_viewmodel_sample.di.ViewModelKey
import com.wispapp.gcpsoftwaretestapp.core.extensions.ViewModelClassMap
import com.wispcoolwisp.dagger_viewmodel_sample.viewmodel.CocaColaViewModel
import com.wispcoolwisp.dagger_viewmodel_sample.viewmodel.CocaColaViewModelImpl
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
        // Define the return type to be the abstraction, but ViewModelProviders 'gets' the implementation
        @Provides
        fun cocaColaViewModel(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ): CocaColaViewModel =
            ViewModelProviders.of(target, factory).get(CocaColaViewModelImpl::class.java)
    }

    // Used so fragments can get the ViewModel from their activity without knowing what the implementation is
    @Module
    class ProvideViewModelAbstractionMap {
        @Provides
        fun viewModelClassMap(): ViewModelClassMap =
            mapOf(CocaColaViewModel::class.java to CocaColaViewModelImpl::class.java)
    }

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(CocaColaViewModel::class)
        // another small alteration, using the abstract class as the ViewModelKey and return type
        fun cocaColaViewModel(dataSource: DataSource): ViewModel =
            CocaColaViewModelImpl(dataSource)
    }
}