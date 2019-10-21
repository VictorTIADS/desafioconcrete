package com.example.desafioconcrete.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioconcrete.R
import com.example.desafioconcrete.model.modeldetails.ItemDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_detail.view.*

class AdapterDetails(
    private val context: Context,
    private val detailsList: ArrayList<ItemDetails>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var detail = detailsList[position]
        var title = holder.itemView.hint3
        var description = holder.itemView.lblDescricaoRepositorioDetails
        var username = holder.itemView.hint2
        var image = holder.itemView.imageViewDetails
        var link = detail.html_url

        title.text = detail.title
        description.text = detail.body
        username.text = detail.user.login

        ContextCompat.getDrawable(context, R.drawable.owner)?.let {
            Picasso.get().load(detail.user.avatar_url).placeholder(it).centerCrop().resize(1000, 1000)
                .into(image)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_detail, parent, false)
        return ViewlHolder(view)
    }

    override fun getItemCount() = detailsList.size


    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)
}