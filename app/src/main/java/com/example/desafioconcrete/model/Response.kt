package com.example.desafioconcrete.model

data class Response(val total_count:Int,val incomplete_results:Boolean, val items:ArrayList<ItemPropities>)