package com.example.mvvmproducts.AllProducts.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lec5.Product
import com.example.mvvmproducts.Model.Repo
import com.example.mvvmproducts.Network.APIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repo: Repo) : ViewModel() {

    private val _productsState = MutableStateFlow<APIState>(APIState.Loading)
    val productsState: StateFlow<APIState> = _productsState

    init {
        viewModelScope.launch {
            repo.getProducts().collect { data  ->
                _productsState.value = data
            }
        }
    }

    fun insertToFav(product : Product){
       viewModelScope.launch(Dispatchers.IO) {
         repo.insertProduct(product)
       }
    }
}