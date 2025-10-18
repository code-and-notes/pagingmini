package com.android.learning.pagingmini.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.learning.pagingmini.db.converters.StringListConverter
import com.android.learning.pagingmini.db.dao.ProductDao
import com.android.learning.pagingmini.db.dao.ProductMetaDao
import com.android.learning.pagingmini.db.dao.RemoteKeyDao
import com.android.learning.pagingmini.db.dao.ReviewDao
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.db.entities.ProductMetaDTO
import com.android.learning.pagingmini.db.entities.RemoteKeyDTO
import com.android.learning.pagingmini.db.entities.ReviewDTO
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        ProductDTO::class,
        ReviewDTO::class,
        ProductMetaDTO::class,
        RemoteKeyDTO::class

    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun reviewDao(): ReviewDao
    abstract fun productMetaDao(): ProductMetaDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}