package com.android.learning.pagingmini.db.dao

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.db.entities.ProductMetaDTO
import com.android.learning.pagingmini.db.entities.ReviewDTO
import com.android.learning.pagingmini.network.data.Product
import com.android.learning.pagingmini.network.data.toProductDTO
import com.android.learning.pagingmini.network.data.toProductMetaDTO
import com.android.learning.pagingmini.network.data.toReviewDTOList
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

//    @Insert
//    suspend fun insertAllProductsDTO(products: List<ProductDTO>)

    @Transaction
    suspend fun insertProducts(products: List<Product>){
        products.forEach { product ->
            val productId = insertProductDTO(product.toProductDTO()).toInt()
            val reviews = product.toReviewDTOList().map { it.copy(productId = productId) }
            Log.w("ProductDao", "Inserting productId: $productId with ${reviews.size} reviews")
            insertReviewsDTO(reviews)

            val productMeta = product.toProductMetaDTO().copy(productId = productId)
            insertProductMetaDTO(productMeta)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviewsDTO(reviews: List<ReviewDTO>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProductMetaDTO(meta: ProductMetaDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDTO(product: ProductDTO): Long


    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): ProductDTO?

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getPagingSource(): PagingSource<Int, ProductDTO>

    @Transaction
    suspend fun clearAllData() {
        Log.w("ProductDao", "Clearing all data from products, reviews, and product_meta tables")
        deleteAllReviews()
        deleteAllProductMeta()
        deleteAllProducts()
    }

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM reviews")
    suspend fun deleteAllReviews()

    @Query("DELETE FROM product_meta")
    suspend fun deleteAllProductMeta()

}
