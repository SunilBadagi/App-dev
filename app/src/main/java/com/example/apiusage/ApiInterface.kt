package com.example.apiusage

import Mydata
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProductData(): Call<Mydata>
}