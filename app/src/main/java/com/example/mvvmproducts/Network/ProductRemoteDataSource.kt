package com.example.mvvmproducts.Network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRemoteDataSource(private val apiService: APIservice) {

    fun getProducts(): Flow<APIState> = flow {
        emit(APIState.Loading)
        try {
            val response = apiService.getProducts()
            emit(APIState.Success(response.products))
        } catch (e: Exception) {
            emit(APIState.Failure(e))
        }
    }
    }


