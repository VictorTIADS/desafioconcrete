package com.example.desafioconcrete.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioconcrete.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        loadToolBar()
        loadToolBarTitle("Nome do Repositório")
    }

    private fun loadToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadToolBarTitle(repositoryName: String) {
        supportActionBar?.title = repositoryName
    }
}
