package com.example.mvvmproducts.AllProducts.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lec5.Product
import com.example.mvvmproducts.Model.Repo
import com.example.mvvmproducts.Network.APIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repo: Repo) : ViewModel() {


    val productsState: StateFlow<APIState> = repo.productsState

    init {
        viewModelScope.launch {
            repo.getProducts()
        }
    }

    fun insertToFav(product : Product){
       viewModelScope.launch(Dispatchers.IO) {
         repo.insertProduct(product)
       }
    }
}