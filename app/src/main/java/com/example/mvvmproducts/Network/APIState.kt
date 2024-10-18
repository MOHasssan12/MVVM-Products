package com.example.mvvmproducts.Network

import com.example.lec5.Product

sealed class APIState {
    class Success(val data: List<Product>) : APIState()
    class Failure(val msg: Throwable) : APIState()
    object Loading : APIState()
}

