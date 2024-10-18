package com.example.mvvmproducts.Model

import com.example.lec5.Product
import com.example.mvvmproducts.DB.ProductLocalDataSource
import com.example.mvvmproducts.Network.APIState
import com.example.mvvmproducts.Network.ProductRemoteDataSource
import com.example.mvvmproducts.Network.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Repo(private val localSource : ProductLocalDataSource , private val remoteSource: ProductRemoteDataSource) {

    private val _productsState = MutableStateFlow<APIState>(APIState.Loading)
    val productsState: StateFlow<APIState> = _productsState.asStateFlow()

    companion object {
        @Volatile
        private var INSTANCE: Repo? = null

        fun getInstance(localDataSource: ProductLocalDataSource , remoteDataSource: ProductRemoteDataSource): Repo? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Repo(localDataSource,remoteDataSource)
                INSTANCE
            }
        }
    }

    fun getProducts(): Flow<APIState> {
        return remoteSource.getProducts()
    }

    suspend fun insertProduct(product: Product) {
            localSource.insertProduct(product)
    }

     fun getAllFav(): Flow<List<Product>> {
        return localSource.getAllFav()
    }

    suspend fun delete(product: Product) {
        localSource.delete(product)
    }
}