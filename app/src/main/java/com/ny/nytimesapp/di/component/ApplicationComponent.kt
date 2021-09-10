package com.ny.nytimesapp.di.component

import android.app.Application
import com.ny.nytimesapp.di.module.RetrofitModule
import com.ny.nytimesapp.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {

    fun plusActivityComponent(): ActivityComponent

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}