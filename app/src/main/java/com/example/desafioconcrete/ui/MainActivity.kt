package com.example.desafioconcrete.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioconcrete.Adapter.AdapterRepositories
import com.example.desafioconcrete.R
import com.example.desafioconcrete.listener.ScrollListener
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_mainactivity.*
import org.jetbrains.anko.appcompat.v7.coroutines.onQueryTextListener
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: AdapterRepositories
    lateinit var mainToolbar:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        setSwipeOnListener()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setToolBar()
        setObservable()
        setAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val item = menu?.findItem(R.id.item_menu_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                   controlView(View.VISIBLE,View.GONE,View.GONE,View.GONE,false)
                    viewModel.getSearch(query)

                }

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }



    private fun setSwipeOnListener() {
        SwipeLayout.setOnRefreshListener {
            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE)
            setObservable()
        }
    }

    private fun setToolBar(){
        mainToolbar = toolbar
        setSupportActionBar(mainToolbar)
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

        viewModel.getLiveDataSearch().observe(this, Observer {
            updateListSearch(it)
        })
    }
    private fun updateListSearch(it: ArrayList<ItemPropities>?) {
        it?.let {
            adapter.repositoriesList.clear()
            adapter.repositoriesList = it

            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE, false)
            adapter.notifyDataSetChanged()

            viewModel.getCollectionAll().value = null
        }
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






