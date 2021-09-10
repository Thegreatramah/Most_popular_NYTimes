package com.ny.nytimesapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ny.nytimesapp.viewModel.factory.ViewModelFactory
import com.kk.nytimesapp.di.ViewModelKey
import com.ny.nytimesapp.di.scope.ActivityScope
import com.ny.nytimesapp.viewModel.NewsListActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @ActivityScope
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(NewsListActivityViewModel::class)
    abstract fun bindActivityViewModel(viewModel: NewsListActivityViewModel): ViewModel
}