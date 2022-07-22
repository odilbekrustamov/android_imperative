package com.exsample.android_imperative.utils

import android.widget.ImageView
import com.exsample.android_imperative.model.TVShow

interface TvShowAdapterClickListner {

    fun onItemClick(tvShow: TVShow, imageView: ImageView)

}