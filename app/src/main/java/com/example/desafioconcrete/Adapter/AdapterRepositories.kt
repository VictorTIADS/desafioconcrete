package com.example.desafioconcrete.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioconcrete.Constants.Constants
import com.example.desafioconcrete.R
import com.example.desafioconcrete.model.ItemPropities
import com.example.desafioconcrete.ui.DetailsActivity
import com.example.desafioconcrete.ui.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterRepositories(
    private val context: Context,
    var repositoriesList: ArrayList<ItemPropities>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mMainActivity: MainActivity

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var repositorio = repositoriesList[position]


        var nomeRepositorio = holder.itemView.hintTitle
        var descricaoRepositorio = holder.itemView.hintDescription
        var username = holder.itemView.lblUsername
        var NFork = holder.itemView.hintNumFork
        var NStar = holder.itemView.lblNumeroStar
        var Language = holder.itemView.hintLang
        var imageR = holder.itemView.hintImage


        nomeRepositorio.text = repositorio.name
        descricaoRepositorio.text = repositorio.description
        username.text = repositorio.owner.login
        NFork.text = repositorio.forks_count.toString()
        NStar.text = repositorio.stargazers_count.toString()
        Language.text = repositorio.language


        ContextCompat.getDrawable(context, R.drawable.owner)?.let {
            Picasso.get().load(repositorio.owner.avatar_url).placeholder(
                it
            ).centerCrop().resize(500, 500)
                .into(imageR)
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra(Constants.REPOSITORIO,repositorio.name)
            intent.putExtra(Constants.CRIADOR,repositorio.owner.login)
            context.startActivity(intent)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewlHolder(view)
    }

    override fun getItemCount(): Int {

        return repositoriesList.size
    }


}

class ViewlHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)