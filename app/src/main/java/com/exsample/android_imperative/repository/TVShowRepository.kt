package com.exsample.android_imperative.repository

import com.exsample.android_imperative.db.TVShowDao
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.networking.TVShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val service: TVShowService, private val tvShowDao: TVShowDao) {

    /**
     * Retrofit Related
     */

    suspend fun apiTVShowPopular(page: Int) = service.apiTVShowPopular(page)
    suspend fun apiTvShowDetails(q: Int) = service.apiTvShowDetails(q)

    /**
     * Room Related
     */

    suspend fun getTVShowsFromDB() = tvShowDao.getTVShowsFromDB()
    suspend fun insertTVShowToDB(tvShow: TVShow) = tvShowDao.insertTVShowToDB(tvShow)
    suspend fun deleteTvShowsFromDB() = tvShowDao.deleteTvShowsFromDB()

}