package com.wispapp.gcpsoftwaretestapp.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wispapp.gcpsoftwaretestapp.core.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class AppModule {

    @Provides
    @AppScope
    fun viewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val creator = providers[modelClass] ?: providers
                    .asIterable()
                    .firstOrNull { it.key.isAssignableFrom(modelClass) }
                    ?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")

                try {
                    @Suppress("UNCHECKED_CAST")
                    return creator.get() as T
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
        }
}