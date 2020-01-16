package com.wispapp.gcpsoftwaretestapp.core.di.components

import android.app.Application
import com.wispapp.gcpsoftwaretestapp.core.application.App
import com.wispapp.gcpsoftwaretestapp.core.di.modules.ActivityInjectionModule
import com.wispapp.gcpsoftwaretestapp.core.di.modules.AppModule
import com.wispapp.gcpsoftwaretestapp.core.di.scope.AppScope
import com.wispcoolwisp.dagger_viewmodel_sample.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityInjectionModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
