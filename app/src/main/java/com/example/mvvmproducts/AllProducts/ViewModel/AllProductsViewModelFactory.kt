package com.example.mvvmproducts.AllProducts.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproducts.Model.Repo

class AllProductsViewModelFactory(private val repo: Repo?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllProductsViewModel::class.java)){
            repo?.let { AllProductsViewModel(it) } as T
        }else{
            throw IllegalArgumentException("ViewModel Class not Found")
        }
    }
}

