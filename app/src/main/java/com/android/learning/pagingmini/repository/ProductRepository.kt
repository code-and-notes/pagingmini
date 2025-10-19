package com.android.learning.pagingmini.repository

import com.android.learning.pagingmini.db.dao.ProductDao
import com.android.learning.pagingmini.network.ProductService

import javax.inject.Inject

class ProductRepository @Inject constructor(
    val productDao: ProductDao,
    val productService: ProductService
) {


    suspend fun getAndInsertProducts() {
        val response = productService.getProducts()
        val products = response.products
        productDao.insertProducts(products)
    }

}