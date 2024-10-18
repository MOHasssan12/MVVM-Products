package com.example.mvvmproducts.AllProducts.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproducts.FavProducts.View.OnProductFavClickListener
import com.example.lec5.DB.ProductDao
import com.example.lec5.DB.ProductDatabase
import com.example.lec5.Product
import com.example.mvvmproducts.AllProducts.ViewModel.AllProductsViewModel
import com.example.mvvmproducts.AllProducts.ViewModel.AllProductsViewModelFactory
import com.example.mvvmproducts.DB.ProductLocalDataSource
import com.example.mvvmproducts.Model.Repo
import com.example.mvvmproducts.Network.APIState
import com.example.mvvmproducts.Network.ProductRemoteDataSource
import com.example.mvvmproducts.Network.RetrofitHelper
import com.example.mvvmproducts.R
import kotlinx.coroutines.launch

class AllProducts : AppCompatActivity(), OnProductFavClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ProductAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var viewModel: AllProductsViewModel
    lateinit var products: List<Product>
    lateinit var allProductsViewModelFactory: AllProductsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_products)
        recyclerView = findViewById(R.id.rv_prod)

        val myProductDao: ProductDao = ProductDatabase.getInstance(this).getproductDao()
        val localDataSource = ProductLocalDataSource(myProductDao)
        val remoteSource = ProductRemoteDataSource(RetrofitHelper.service)

        allProductsViewModelFactory = AllProductsViewModelFactory(Repo.getInstance(localDataSource, remoteSource))
        viewModel = ViewModelProvider(this, allProductsViewModelFactory).get(AllProductsViewModel::class.java)
        mAdapter = ProductAdapter(this)
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
        lifecycleScope.launch {
            viewModel.productsState.collect { state ->
                when (state) {
                    is APIState.Loading -> {
                    }
                    is APIState.Success -> {
                        mAdapter.submitList(state.data)
                    }
                    is APIState.Failure -> {
                        Toast.makeText(this@AllProducts, "Error: ${state.msg}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onProductClick(product: Product) {
        viewModel.insertToFav(product)
        Toast.makeText(this, "Product added to favorites", Toast.LENGTH_SHORT).show()
    }
}
