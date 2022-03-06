package com.jbntech.mvvmlearn.api

import com.jbntech.mvvmlearn.source.remote.RetrofitRemoteApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //create a client
    private val client = OkHttpClient.Builder().apply {

        //add an intercepter to the client to be able to add headers
//           addInterceptor(RetrofitInterceptor())
    }.build()


    //create an object of a retrofit instance
    private val retrofitInstance by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //create the retrofit instance from the object
    val api: RetrofitRemoteApi by lazy {
        retrofitInstance.create(RetrofitRemoteApi::class.java)
    }

    //this api variable will be called inside the repository class
}