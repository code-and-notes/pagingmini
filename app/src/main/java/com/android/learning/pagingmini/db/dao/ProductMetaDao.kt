package com.android.learning.pagingmini.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.learning.pagingmini.db.entities.ProductMetaDTO

@Dao
interface ProductMetaDao {

    @Insert
    suspend fun insertAllProductMeta(productMetaDTOs: List<ProductMetaDTO>)

    @Query("SELECT * FROM product_meta WHERE productId = :productId LIMIT 1")
    suspend fun getProductMetaById(productId: Int): ProductMetaDTO?

}