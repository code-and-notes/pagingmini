package com.android.learning.pagingmini.db.data

import androidx.room.ColumnInfo

data class ProductDimensions(
    @ColumnInfo(name = "width") val width: Double,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "depth") val depth: Double
)