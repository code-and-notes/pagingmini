package com.android.learning.pagingmini.di

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class
)
@Module
class UtilityModule {

    @Provides
    @Singleton
    @DatabaseName
    fun provideDatabaseName(): String {
        return "pagingmini_db"
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName