package com.example.desafioconcrete.Adapter

import android.content.Context
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
        var title = holder.itemView.lblTitleName
        var description = holder.itemView.lblDescricaoRepositorioDetails
        var username = holder.itemView.lblUsernameDetails
        var image = holder.itemView.imageViewDetails

        title.text = detail.title
        description.text = detail.body
        username.text = detail.user.login

        ContextCompat.getDrawable(context, R.drawable.owner)?.let {
            Picasso.get().load(detail.user.avatar_url).placeholder(it).centerCrop().resize(1000, 1000)
                .into(image)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_detail, parent, false)
        return ViewlHolder(view)
    }

    override fun getItemCount() = detailsList.size


    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)
}