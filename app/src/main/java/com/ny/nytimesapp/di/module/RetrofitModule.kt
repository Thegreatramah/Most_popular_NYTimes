package com.ny.nytimesapp.di.module

import android.app.Application
import com.ny.nytimesapp.BuildConfig
import com.ny.nytimesapp.di.scope.ApplicationScope
import com.ny.nytimesapp.network.ResultResponseApi
import com.ny.nytimesapp.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun provideResultResponseApi(retrofit: Retrofit): ResultResponseApi =
            retrofit.create(ResultResponseApi::class.java)


    @Provides
    @ApplicationScope
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

    @ApplicationScope
    @Provides
    fun provideNetworkUtil(application: Application): NetworkUtil =
            NetworkUtil(application)


    @Provides
    @ApplicationScope
    fun provideOkHttpCLient(interceptor: Interceptor): OkHttpClient =
            OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    @Provides
    @ApplicationScope
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val url = request.url().newBuilder()
                .build()
        val newRequest = request.newBuilder()
                .url(url)
                .build()
        chain.proceed(newRequest)
    }


}