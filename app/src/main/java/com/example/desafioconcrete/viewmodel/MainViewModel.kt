package com.example.desafioconcrete.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.desafioconcrete.model.Response
import com.example.desafioconcrete.repository.BaseRepository

class MainViewModel : ViewModel() {





    private val repository = BaseRepository()
    private lateinit var repositoriesLiveData: LiveData<Response>

    init {
        initRequest()
    }

    fun initRequest() {
        repositoriesLiveData = repository.getRepositories(1)
    }


    fun getLiveData() = repositoriesLiveData


}