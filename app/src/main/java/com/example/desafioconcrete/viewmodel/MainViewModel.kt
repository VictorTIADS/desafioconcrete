package com.example.desafioconcrete.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.repository.BaseRepository

class MainViewModel : ViewModel() {

    private val repository = BaseRepository()
    private var repositoriesLiveData = MutableLiveData<ArrayList<ItemPropities>>()
    private val collectionAll = MutableLiveData<ArrayList<ItemPropities>>()
    private val collectionSearch = MutableLiveData<ArrayList<ItemPropities>>()

    var page = 1

    init {
        initRequest()
    }

    fun initRequest() {
        repository.getRepositories(page,{

         repositoriesLiveData.value =  it?.items
        },{

        })
    }

    fun loadMore(){
        page++
        repository.getRepositories(page,{
            if (it != null) {
                repositoriesLiveData.value?.addAll(it.items)
                collectionAll.value = it.items
            }

        },{

        })
    }
    fun getSearch(query:String){
        repository.getRepositoriesOnSeach(query,{
            collectionSearch.value = it?.items

        },{

        })
    }

    fun getLiveDataSearch() = collectionSearch

    fun getLiveData() = repositoriesLiveData

    fun getLiveDataValue() = repositoriesLiveData.value

    fun getCollectionAll() = collectionAll

}