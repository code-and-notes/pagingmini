package com.android.learning.pagingmini.di

import android.content.Context
import androidx.room.Room
import com.android.learning.pagingmini.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context,@DatabaseName databaseName:String): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Provides
    @Singleton
    fun provideReviewDao(database: AppDatabase) = database.reviewDao()

    @Provides
    @Singleton
    fun provideProductMetaDao(database: AppDatabase) = database.productMetaDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database: AppDatabase) = database.remoteKeyDao()
}