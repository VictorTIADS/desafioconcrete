package com.example.desafioconcrete.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioconcrete.connection.RetrofitRepositories
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.model.Response
import com.example.desafioconcrete.repository.BaseRepository

class MainViewModel : ViewModel() {


    private val repository = BaseRepository()
    private var repositoriesLiveData: LiveData<Response>

    init {
        repositoriesLiveData = repository.getRepositories()
    }


    fun getLiveData() = repositoriesLiveData


}