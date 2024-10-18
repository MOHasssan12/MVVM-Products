package com.example.mvvmproducts.FavProducts.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lec5.Product
import com.example.mvvmproducts.Model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllFavProductsViewModel (private val repo: Repo) : ViewModel()   {


    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList


    fun getAllFav(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllFav()
                .collect{productList ->
                    _productList.postValue(productList)
                }
        }
    }

    fun delete(product : Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(product)
        }
    }

}

