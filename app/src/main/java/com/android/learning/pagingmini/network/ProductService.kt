package com.android.learning.pagingmini.network


import com.android.learning.pagingmini.network.data.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ProductService {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 10,
        @Query("skip") skip: Int = 0
    ): ProductResponse

}