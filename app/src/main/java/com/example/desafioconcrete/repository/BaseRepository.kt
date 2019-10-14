package com.example.desafioconcrete.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafioconcrete.connection.RetrofitRepositories
import com.example.desafioconcrete.model.Response
import retrofit2.Call
import retrofit2.Callback


class BaseRepository {


    fun getRepositories(): LiveData<Response> {

        val items = MutableLiveData<Response>()

        var call = RetrofitRepositories().interfaceData()
        call.getRepositore("language:Java", "stars", 1).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.code() == 200) {

                    val resposta = response.body()
                    resposta?.let {
                        items.value = resposta
                        Log.i("spk", items.value?.items?.get(0).toString())
                    }
                }
            }
        })

        return items
    }


}