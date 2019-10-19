package com.example.desafioconcrete.repository

import androidx.lifecycle.MutableLiveData
import com.example.desafioconcrete.connection.RetrofitRepositories
import com.example.desafioconcrete.model.Response
import retrofit2.Call
import retrofit2.Callback


class BaseRepository {

    fun getRepositories(page:Int, success: (Response?) -> Unit, fail:(String?) -> Unit): MutableLiveData<Response> {

        val items = MutableLiveData<Response>()

        var call = RetrofitRepositories().interfaceData()
        call.getRepositore("language:Java", "stars", page).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                fail(t.message)

            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.code() == 200) {
                    val resposta = response.body()
                    success(resposta)
                }
            }
        })

        return items
    }




}