package com.example.desafioconcrete.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioconcrete.Adapter.AdapterRepositories
import com.example.desafioconcrete.R
import com.example.desafioconcrete.connection.RetrofitRepositories
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.model.Response
import com.example.desafioconcrete.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {


    var isScrolling = false
    private var currentItems: Int = 0
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0
    private var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       viewModel()


        SwipeLayout.setOnRefreshListener {
            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE)

            viewModel()

        }








    }

    private fun viewModel(): MainViewModel {
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            it?.let {

                val adapter = setAdapter(it)

                controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE, false)
                adapter.notifyDataSetChanged()


            }
        })

        return viewModel
    }

    private fun setAdapter(it: Response): AdapterRepositories {
        val adapter = AdapterRepositories(this, it.items)
        var layoutManeger = LinearLayoutManager(this)
        val recyclerViewvar = recicleView
        recyclerViewvar.layoutManager = layoutManeger
        recyclerViewvar.adapter = adapter
//        scrollListener(layoutManeger,adapter)


        return adapter

    }

//    fun scrollListener(layoutManeger:LinearLayoutManager,adapter:AdapterRepositories){
//        recicleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    isScrolling = true
//
//                }
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                Log.i(
//                    "aspk",
//                    "CurrentItems:${currentItems} scrolloutItems:${scrollOutItems} = ${totalItems} isScrolling:${isScrolling}"
//                )
//                currentItems = layoutManeger.childCount
//                totalItems = layoutManeger.itemCount
//                scrollOutItems = layoutManeger.findFirstVisibleItemPosition()
//
//
//                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
//                    //data fetch
//                    isScrolling = false
//                    controlView(View.GONE, View.GONE, View.VISIBLE, View.VISIBLE, false)
//                    page = page + 1
//
//
//
//
//
//
//
//
//
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//
//
//        })
//    }



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









