package com.enty.ilinkcatsandducks.di

import com.enty.ilinkcatsandducks.common.Constants
import com.enty.ilinkcatsandducks.data.remote.CatsApi
import com.enty.ilinkcatsandducks.data.remote.DucksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCatsApi() =
        Retrofit.Builder()
        .baseUrl(Constants.CATSBASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatsApi::class.java)

    @Provides
    @Singleton
    fun provideDucksApi() =
        Retrofit.Builder()
        .baseUrl("https://random-d.uk")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DucksApi::class.java)


}