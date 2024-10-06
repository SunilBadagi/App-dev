package com.example.apiusage

import Mydata
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recylerview)
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<Mydata?> {
            override fun onResponse(p0: Call<Mydata?>, p1: Response<Mydata?>) {
                //api call succesful
                var responseBody=p1.body()
                val productList=responseBody?.products!!
                myAdapter =MyAdapter(this@MainActivity,productList)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(p0: Call<Mydata?>, p1: Throwable) {
                //if api fails
                Log.d("Main Activity","onFailure" + p1.message)
            }
        })

    }
}