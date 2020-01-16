package com.wispapp.gcpsoftwaretestapp.core.di.modules

import com.wispapp.gcpsoftwaretestapp.core.di.scope.ActivityScope
import com.wispapp.gcpsoftwaretestapp.ui.main.ImageFragment
import com.wispapp.gcpsoftwaretestapp.ui.main.MainActivity
import com.wispapp.gcpsoftwaretestapp.ui.main.TextFragment
import com.wispapp.gcpsoftwaretestapp.ui.main.WebViewFragment
import com.wispcoolwisp.dagger_viewmodel_sample.di.modules.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule.InjectViewModel::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule.InjectViewModel::class])
    abstract fun contributeTextFragmentInjector(): TextFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule.InjectViewModel::class])
    abstract fun contributeImageFragmentInjector(): ImageFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule.InjectViewModel::class])
    abstract fun contributeWebViewFragmentInjector(): WebViewFragment
}