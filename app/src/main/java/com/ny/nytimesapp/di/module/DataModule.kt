package com.ny.nytimesapp.di.module

import com.ny.nytimesapp.di.scope.ActivityScope
import com.ny.nytimesapp.network.RemoteData
import com.ny.nytimesapp.network.RemoteDataImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    @ActivityScope
    fun bindRemoteData(remoteDataImpl: RemoteDataImpl): RemoteData
}