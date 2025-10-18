package com.android.learning.pagingmini.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.learning.pagingmini.ProductsRemediator
import com.android.learning.pagingmini.db.AppDatabase
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.network.ProductService
import com.android.learning.pagingmini.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val db : AppDatabase,
    private val apiService: ProductService
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllProductsFromCache(): Flow<PagingData<ProductDTO>>  {

        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false),
            remoteMediator = ProductsRemediator(apiService, db),
            pagingSourceFactory = { db.productDao().getPagingSource()}
        ).flow
            .cachedIn(viewModelScope)
    }

    fun getAndInsertProducts(): Boolean {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAndInsertProducts()
            }
        }
        catch (e: Exception) {
            return false
        }
        return true
    }

}