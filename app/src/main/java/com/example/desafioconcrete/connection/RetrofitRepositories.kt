package com.example.desafioconcrete.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepositories {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun interfaceData() = retrofit.create(InterfaceData::class.java)


}