package com.example.mvvmproducts.FavProducts.View

import androidx.recyclerview.widget.DiffUtil
import com.example.lec5.Product

class ProductsDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
    return oldItem == newItem
    }

}