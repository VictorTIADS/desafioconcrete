package com.example.desafioconcrete.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.repository.BaseRepository
import java.lang.Exception


class MainViewModel : ViewModel() {

    private val repository = BaseRepository()
    private var repositoriesLiveData = MutableLiveData<ArrayList<ItemPropities>>()
    private val collectionAll = MutableLiveData<ArrayList<ItemPropities>>()
    private val collectionSearch = MutableLiveData<ArrayList<ItemPropities>>()

    var page = 1
    var querymaster = "language:Java"


    init {
        initRequest(querymaster)
    }

    fun initRequest(query: String,paging:Int = page) {
        repository.getRepositories(paging, query, "stars", {
            querymaster = query
            repositoriesLiveData.value = it?.items
        }, {

        })
    }

    fun loadMore() {
        page++
        repository.getRepositories(page, querymaster, "stars", {
            if (it != null) {
                Log.i("gcc",querymaster)
                repositoriesLiveData.value?.addAll(it.items)
                collectionAll.value = it.items
            }

        }, {

        })
    }
//    fun getSearch(query:String){
//        repository.getRepositoriesOnSeach(query,{
//            collectionSearch.value = it?.items
//
//        },{
//
//        })
//    }

    fun getLiveDataSearch() = collectionSearch

    fun getLiveData() = repositoriesLiveData

    fun getLiveDataValue() = repositoriesLiveData.value

    fun getCollectionAll() = collectionAll

}