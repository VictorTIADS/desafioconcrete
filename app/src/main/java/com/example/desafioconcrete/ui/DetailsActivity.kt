package com.example.desafioconcrete.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioconcrete.Adapter.AdapterDetails
import com.example.desafioconcrete.Constants.Constants
import com.example.desafioconcrete.R
import com.example.desafioconcrete.connection.RetrofitDetailsRepository
import com.example.desafioconcrete.model.modeldetails.ItemDetails
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_detailsactivity.*
import kotlinx.android.synthetic.main.error_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    lateinit var repositorio: String
    lateinit var criador: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        repositorio = intent.getStringExtra(Constants.REPOSITORIO)
        criador = intent.getStringExtra(Constants.CRIADOR)

        loadToolBar()
        loadToolBarTitle(repositorio)


        tabHost.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    1->{
                        controlView(View.GONE,View.VISIBLE,View.GONE)
                        getDetails(criador,repositorio,"issues")
                    }
                    0->{
                        controlView(View.GONE,View.VISIBLE,View.GONE)
                        getDetails(criador,repositorio,"pulls")
                    }
                }

            }
        })

        val call = RetrofitDetailsRepository().interfaceData()
        call.getDetailsRepositor(criador,repositorio,"pulls").enqueue(object : Callback<ArrayList<ItemDetails>> {
            override fun onFailure(call: Call<ArrayList<ItemDetails>>, t: Throwable) {
                Log.i("aspk",t.message)
                Log.i("aspk",call.request().url().toString())
            }

            override fun onResponse(call: Call<ArrayList<ItemDetails>>, response: Response<ArrayList<ItemDetails>>) {
                if(response.isSuccessful){
                    var resposta = response.body()
                    resposta?.let {item->

                        Log.i("aspk",call.request().url().toString())
                        if(resposta.isNotEmpty()){

                            initUI(resposta)
                            controlView(View.VISIBLE,View.GONE, View.GONE)
                        } else if (resposta.isEmpty()){

                            controlView(View.GONE,View.GONE, View.VISIBLE)
                        }

                    }
                }

            }
        })


    }

    private fun getDetails(criador:String,repositorio:String,request:String){
        val call = RetrofitDetailsRepository().interfaceData()
        call.getDetailsRepositor(criador,repositorio,request).enqueue(object : Callback<ArrayList<ItemDetails>> {
            override fun onFailure(call: Call<ArrayList<ItemDetails>>, t: Throwable) {
                Log.i("aspk",t.message)
                Log.i("aspk",call.request().url().toString())
            }

            override fun onResponse(call: Call<ArrayList<ItemDetails>>, response: Response<ArrayList<ItemDetails>>) {
                if(response.isSuccessful){
                    var resposta = response.body()
                    resposta?.let {item->

                        Log.i("aspk",call.request().url().toString())
                        if(resposta.isNotEmpty()){


                            initUI(resposta)
                            controlView(View.VISIBLE,View.GONE, View.GONE)
                        }else if (resposta.isEmpty()){

                            controlView(View.GONE,View.GONE, View.VISIBLE)
                        }


                    }
                }

            }
        })
    }


    fun initUI(list: ArrayList<ItemDetails>) {
        val adapter = AdapterDetails(this, list)
        val layoutManager = LinearLayoutManager(this)
        val recycleView = recycleViewDetails
        recycleView.layoutManager = layoutManager
        recycleView.adapter = adapter
    }

    private fun loadToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadToolBarTitle(repositoryName: String) {
        supportActionBar?.title = repositoryName
    }

    private fun controlView(recycle:Int,load:Int, emptyState: Int){
        recycleViewDetails.visibility = recycle
        loadspinkitmain.visibility = load
        empty_state.visibility = emptyState
    }

    fun ShowOnWeb(view: View){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://github.com/${criador}/${repositorio}/")
        this.startActivity(intent)
    }

}
