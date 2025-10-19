package com.android.learning.pagingmini.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.android.learning.pagingmini.db.AppDatabase
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.db.entities.RemoteKeyDTO
import com.android.learning.pagingmini.network.ProductService

@OptIn(ExperimentalPagingApi::class)
class ProductsRemediator(
    private val productService: ProductService,
    private val db : AppDatabase,
    private val pageSize: Int = 10
) : RemoteMediator<Int, ProductDTO>() {

    private val remoteKeyDao = db.remoteKeyDao()
    private val productDao = db.productDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductDTO>
    ): MediatorResult {
        try {
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextKey?.minus(pageSize) ?: 0
                }

                LoadType.PREPEND -> {
                    val firstRemoteKey = getRemoteKeyForFirstItem(state)
                    firstRemoteKey?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastRemoteKey = getRemoteKeyForLastItem(state)
                    lastRemoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            val response = productService.getProducts(skip = offset)
            val products = response.products
            val endOfPaginationReached = products.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearRemoteKeys()
                    productDao.clearAllData()
                }
                val remoteKeys = products.map { product ->
                    RemoteKeyDTO(
                        productId = product.id,
                        prevKey = if (offset == 0) null else offset - pageSize,
                        nextKey = if (endOfPaginationReached) null else offset + pageSize
                    )
                }
                remoteKeyDao.insertALL(remoteKeys)
                productDao.insertProducts(products)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        }
        catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ProductDTO>): RemoteKeyDTO? {
        val lastItem = state.lastItemOrNull() ?: return null
        return remoteKeyDao.remoteKeyByProductId(lastItem.id)
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ProductDTO>): RemoteKeyDTO? {
        val firstItem = state.firstItemOrNull() ?: return null
        return remoteKeyDao.remoteKeyByProductId(firstItem.id)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ProductDTO>): RemoteKeyDTO? {
        val anchor = state.anchorPosition ?: return null
        val item = state.closestItemToPosition(anchor) ?: return null
        return remoteKeyDao.remoteKeyByProductId(item.id)
    }
}