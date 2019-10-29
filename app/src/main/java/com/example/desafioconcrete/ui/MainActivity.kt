package com.example.desafioconcrete.ui

import android.app.AlertDialog
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
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_mainactivity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.appcompat.v7.coroutines.onClose
import org.jetbrains.anko.appcompat.v7.coroutines.onQueryTextListener
import org.jetbrains.anko.appcompat.v7.coroutines.onSearchClick
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: AdapterRepositories
    lateinit var mainToolbar: Toolbar


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

        menuInflater.inflate(R.menu.main, menu)
        val item = menu?.findItem(R.id.item_menu_search)
        val searchView = item?.actionView as SearchView
        searchView.isIconified = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    controlView(View.VISIBLE, View.GONE, View.GONE, View.GONE, false)
                    viewModel.initRequest(query,1)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_menu_about->{
                alert(Appcompat, "Some text message").show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setSwipeOnListener() {
        SwipeLayout.setOnRefreshListener {
            controlView(View.GONE, View.GONE, View.VISIBLE, View.GONE)
            viewModel.initRequest("language:Java", 1)

        }

    }

    private fun setToolBar() {
        mainToolbar = toolbar

        setSupportActionBar(mainToolbar)
    }

    private fun setObservable() {

        viewModel.getCollectionAll().observe(this, Observer {
            updateList(it)
        })

        viewModel.getLiveData().observe(this, Observer {
            adapter.repositoriesList.clear()
            updateList(it)
            Log.i("aspk", adapter.repositoriesList.size.toString())

        })


    }


    private fun updateList(it: ArrayList<ItemPropities>?) {
        it?.let {
            adapter.repositoriesList.addAll(it)
            Log.i("aspk", adapter.repositoriesList.size.toString())
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






