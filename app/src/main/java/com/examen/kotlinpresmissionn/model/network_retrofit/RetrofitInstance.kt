package com.examen.kotlinpresmissionn.model.network_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL ="http://fakerestapi.azurewebsites.net/"
    fun getRetrofitInstance ():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}