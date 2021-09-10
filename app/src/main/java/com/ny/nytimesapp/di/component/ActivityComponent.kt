package com.ny.nytimesapp.di.component

import com.ny.nytimesapp.di.module.DataModule
import com.ny.nytimesapp.di.module.ViewModelFactoryModule
import com.ny.nytimesapp.di.scope.ActivityScope
import com.ny.nytimesapp.view.NewsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelFactoryModule::class, DataModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(resutActivity: NewsListActivity)
}