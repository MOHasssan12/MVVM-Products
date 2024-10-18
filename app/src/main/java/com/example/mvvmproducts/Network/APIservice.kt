package com.example.mvvmproducts.Network

import com.example.lec5.ProductResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIservice {

    @GET("products")
    suspend fun getProducts() : ProductResponse
}

object RetrofitHelper{
    private const val BASE_URL = "https://dummyjson.com/"
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofitInstance.create(APIservice::class.java)

}
