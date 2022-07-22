package com.exsample.android_imperative.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exsample.android_imperative.R
import com.exsample.android_imperative.databinding.ItemTvshowBinding
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.utils.TvShowAdapterClickListner

class TvShowAdapter(var items: ArrayList<TVShow>, private val tvShowAdapterClickListner: TvShowAdapterClickListner):
    BaseAdapter(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShow: TVShow = items[position]
        if (holder is TVShowViewHolder) {
            Glide.with(holder.binding.ivMovie).load(tvShow.image_thumbnail_path).into(holder.binding.ivMovie)
            holder.binding.tvName.text = tvShow.name
            holder.binding.tvType.text = tvShow.network

            Log.d("TAG", "onBindViewHolder: ")

            ViewCompat.setTransitionName(holder.binding.ivMovie, tvShow.name)
            holder.binding.ivMovie.setOnClickListener {
                tvShowAdapterClickListner.onItemClick(tvShow, holder.binding.ivMovie)

            }
        }
    }



    override fun getItemCount(): Int = items.size

    fun setNewShows(tvShows: ArrayList<TVShow>){
        items.addAll(tvShows)
        notifyDataSetChanged()
    }

    inner class TVShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTvshowBinding.bind(view)
    }
}
