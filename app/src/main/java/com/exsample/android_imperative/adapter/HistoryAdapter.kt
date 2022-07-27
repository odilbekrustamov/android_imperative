package com.exsample.android_imperative.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exsample.android_imperative.R
import com.exsample.android_imperative.databinding.ItemTvshowBinding
import com.exsample.android_imperative.model.TVShow

class HistoryAdapter(var items: ArrayList<TVShow>): BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HistoryViewHolder){
            Glide.with(holder.binding.ivMovie).load(item.image_thumbnail_path).into(holder.binding.ivMovie)
            holder.binding.tvName.text = item.name
            holder.binding.tvType.text = item.network
        }
    }

    override fun getItemCount(): Int = items.size

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTvshowBinding.bind(view)
    }
}