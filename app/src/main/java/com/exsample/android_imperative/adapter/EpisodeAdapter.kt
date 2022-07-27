package com.exsample.android_imperative.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exsample.android_imperative.R
import com.exsample.android_imperative.databinding.ItemEpisodeViewBinding
import com.exsample.android_imperative.model.Episode

class EpisodeAdapter(var items: ArrayList<Episode>): BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode_view, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is EpisodeViewHolder){
            holder.binding.apply {
                tvSeason.text = item.season.toString()
                tvEpisode.text = item.episode.toString()
                tvName.text = item.name
                tvAirDate.text = item.air_date.toString().substring(0, 10)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEpisodeViewBinding.bind(view)
    }

}