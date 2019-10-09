package com.example.desafioconcrete.connection

import com.example.desafioconcrete.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceData {
    @GET("repositories")
    fun getRepositore(

        @Query("q") q: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1

    ): Call<Response>
}