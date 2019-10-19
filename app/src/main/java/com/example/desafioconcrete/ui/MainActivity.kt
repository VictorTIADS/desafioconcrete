package com.example.desafioconcrete.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioconcrete.Adapter.AdapterRepositories
import com.example.desafioconcrete.R
import com.example.desafioconcrete.listener.ScrollListener
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    private lateinit var adapter: AdapterRepositories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SwipeLayout.setOnRefreshListener {
            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE)
            setObservable()
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setObservable()
        setAdapter()
    }

    private fun setObservable() {

        viewModel.getCollectionAll().observe(this, Observer {
            updateList(it)
        })

        viewModel.getLiveData().observe(this, Observer {
            updateList(it)
            Log.i("aspk",adapter.repositoriesList.size.toString())
            adapter.notifyDataSetChanged()
        })
    }

    private fun updateList(it: ArrayList<ItemPropities>?) {
        it?.let {
            adapter.repositoriesList.addAll(it)
            Log.i("aspk",adapter.repositoriesList.size.toString())
            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE, false)
            adapter.notifyDataSetChanged()

            viewModel.getCollectionAll().value = null
        }
    }

    private fun setAdapter() {
        val adapter = AdapterRepositories(this, ArrayList())
        var layoutManeger = LinearLayoutManager(this)
        val recyclerViewvar = recicleView
        recyclerViewvar.layoutManager = layoutManeger
        recyclerViewvar.adapter = adapter
        recicleView.addOnScrollListener(ScrollListener(layoutManeger) {
            controlView(View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, false)
            viewModel.loadMore()




        })


        this.adapter = adapter
    }

    private fun controlView(
        PorgressBar: Int,
        TextView: Int,
        RecycleView: Int,
        Wave: Int,
        Swipe: Boolean = true
    ) {
        splash.visibility = PorgressBar
        recicleView.visibility = RecycleView
        SwipeLayout.isRefreshing = Swipe
        spin_kitWave.visibility = Wave
    }


}


//    fun getRepositories() {
//        var call = RetrofitRepositories().interfaceData()
//        call.getRepositore("language:Java", "stars", 1).enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>, t: Throwable) {
//
//                setContentView(R.layout.error_layout)
//
//            }
//
//            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//                if (response.code() == 200) {
//
//                    val resposta = response.body()
//                    resposta?.let {
//
//                        controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE, false)
//                        initIU(resposta.items)
//
//
//                    }
//                }
//            }
//        })
//    }


//                recicleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//
//                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                            isScrolling = true
//
//                        }
//                        super.onScrollStateChanged(recyclerView, newState)
//                    }
//
//                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                        Log.i(
//                            "aspk",
//                            "CurrentItems:${currentItems} scrolloutItems:${scrollOutItems} = ${totalItems} isScrolling:${isScrolling}"
//                        )
//                        currentItems = layoutManeger.childCount
//                        totalItems = layoutManeger.itemCount
//                        scrollOutItems = layoutManeger.findFirstVisibleItemPosition()
//
//
//                        if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
//                            //data fetch
//                            isScrolling = false
//                            controlView(View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, false)
//                            page = page + 1
//
//
//
//
//
//
//
//                        }
//                        super.onScrolled(recyclerView, dx, dy)
//                    }
//
//
//                })










