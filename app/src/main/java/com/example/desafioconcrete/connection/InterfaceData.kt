package com.example.desafioconcrete.connection

import androidx.lifecycle.LiveData
import com.example.desafioconcrete.model.Response
import com.example.desafioconcrete.model.modeldetails.ItemDetails
import com.example.desafioconcrete.model.modeldetails.ResponseDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InterfaceData {
    @GET("repositories")
    fun getRepositore(

        @Query("q") q: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1

    ): Call<Response>



    @GET("repos/{criador}/{repositorio}/{request}")
    fun getDetailsRepositor(
        @Path("criador") criador:String = "",
        @Path("repositorio") repositorio:String = "",
        @Path("request")request:String = ""



    ):Call<ArrayList<ItemDetails>>



}