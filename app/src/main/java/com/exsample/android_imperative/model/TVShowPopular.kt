package com.exsample.android_imperative.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class TVShowPopular(
    val total: String,
    val page: Int,
    val pages: Int,
    val tv_shows: ArrayList<TVShow>
)

@Entity(tableName = "tv_show")
data class TVShow(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_date") val start_date: String,
    @ColumnInfo(name = "end_date") val end_date: String,
    @ColumnInfo(name = "network") val network: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "image_thumbnail_path") val image_thumbnail_path: String
)