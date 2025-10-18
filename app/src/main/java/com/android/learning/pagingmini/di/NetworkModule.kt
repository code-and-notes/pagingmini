package com.android.learning.pagingmini.di

import com.android.learning.pagingmini.network.ProductService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl() = "https://dummyjson.com/"

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesRetrofitBuilder(@BaseUrl baseUrl: String,gson: Gson,httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductService  = retrofit.create(ProductService::class.java)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl