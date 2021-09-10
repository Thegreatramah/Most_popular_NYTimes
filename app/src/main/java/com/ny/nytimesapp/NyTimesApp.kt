package com.ny.nytimesapp

import android.app.Application
import com.ny.nytimesapp.di.component.ApplicationComponent
import com.ny.nytimesapp.di.component.DaggerApplicationComponent

class NyTimesApp : Application() {

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent
}