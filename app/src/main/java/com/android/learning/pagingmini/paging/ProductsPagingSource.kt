package com.android.learning.pagingmini.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.network.ProductService
import com.android.learning.pagingmini.network.data.toProductDTO

class ProductsPagingSource(
    private val productService: ProductService
): PagingSource<Int, ProductDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDTO> {
        val skip = params.key ?: 0
        try {
            val response = productService.getProducts(skip = skip)
            val products = response.products.map { it.toProductDTO() }
            val nextKey = if (products.isEmpty()) {
                null
            } else {
                skip + params.loadSize
            }
            return LoadResult.Page(
                data = products,
                prevKey = if (skip == 0) null else skip - params.loadSize,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
            // Since data is already in local DB, we just fetch from there
    }

    override fun getRefreshKey(state: PagingState<Int, ProductDTO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(10) ?: anchorPage.nextKey?.minus(10)
    }


}