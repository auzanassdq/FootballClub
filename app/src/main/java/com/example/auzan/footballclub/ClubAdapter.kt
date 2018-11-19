package com.example.auzan.footballclub

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by auzan on 11/15/2018.
 * Github: @auzanassdq
 */

class ClubAdapter (private val items: List<Club>,
                   private val listener: (Club) -> Unit)
    : RecyclerView.Adapter<ClubAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUI().createView(AnkoContext.create(parent.context, parent)))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val nameClub: TextView = containerView.find(ItemUI.tvClub)
        private val imageClub: ImageView = containerView.find(ItemUI.ImgClub)

        fun bindItem(items: Club, listener: (Club) -> Unit) {
            nameClub.text = items.name
            items.image?.let { Picasso.get().load(it).fit().into(imageClub) }
            containerView.setOnClickListener { listener(items)}
        }

    }

}