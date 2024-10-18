package com.example.mvvmproducts.FavProducts.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproducts.Model.Repo


class AllFavProductsViewModelFactory (private val repo: Repo?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllFavProductsViewModel::class.java)){
            repo?.let { AllFavProductsViewModel(it) } as T
        }else{
            throw IllegalArgumentException("ViewModel Class not Found")
        }
    }
}