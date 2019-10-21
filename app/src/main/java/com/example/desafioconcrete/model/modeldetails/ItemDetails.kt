package com.example.desafioconcrete.model.modeldetails

import com.example.desafioconcrete.model.Owner

data class ItemDetails(
    val title: String,
    val body: String,
    val html_url:String,
    val user: Owner

)