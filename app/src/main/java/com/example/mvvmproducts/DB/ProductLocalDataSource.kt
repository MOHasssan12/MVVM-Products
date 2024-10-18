package com.example.mvvmproducts.DB

import com.example.lec5.DB.ProductDao
import com.example.lec5.Product
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSource(private val productDao:ProductDao) {

     fun getAllFav() : Flow<List<Product>> {
        return productDao.getAllProducts()
    }
    suspend fun insertProduct(product: Product) {
        productDao.insert(product)
    }
    suspend fun delete(product: Product){
        productDao.delete(product)
    }
}