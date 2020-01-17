package com.wispapp.gcpsoftwaretestapp.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wispapp.gcpsoftwaretestapp.core.common.ImageLoader
import com.wispapp.gcpsoftwaretestapp.core.common.ImageLoaderImpl
import com.wispapp.gcpsoftwaretestapp.core.di.scope.AppScope
import com.wispapp.gcpsoftwaretestapp.core.executors.AppExecutors
import com.wispapp.gcpsoftwaretestapp.core.executors.BackgroundExecutor
import com.wispapp.gcpsoftwaretestapp.core.executors.UiThreadExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Named
import javax.inject.Provider

@Module
class AppModule {

    @Provides
    fun provideImageLoader(appExecutors: AppExecutors): ImageLoader = ImageLoaderImpl(appExecutors)

    @Provides
    fun provideAppExecutors(
        @Named(BACKGROUND_EXECUTOR) backgroundExecutor: Executor,
        @Named(UI_EXECUTOR) uiThreadExecutor: Executor
    ): AppExecutors = AppExecutors(backgroundExecutor, uiThreadExecutor)

    @Provides
    @Named(BACKGROUND_EXECUTOR)
    fun provideBackgroundExecutor(): Executor = BackgroundExecutor()

    @Provides
    @Named(UI_EXECUTOR)
    fun provideUiExecutor(): Executor = UiThreadExecutor()

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

    companion object {

        private const val BACKGROUND_EXECUTOR = "background_executor"
        private const val UI_EXECUTOR = "ui_executor"
    }
}