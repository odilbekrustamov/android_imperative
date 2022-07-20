package com.exsample.android_imperative.repository

import com.exsample.android_imperative.networking.TVShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val service: TVShowService) {

    /**
     * Retrofit Related
     */

    suspend fun apiTVShowPopular(page: Int) = service.apiTVShowPopular(page)
    suspend fun apiTvShowDetails(q: Int) = service.apiTvShowDetails(q)

    /**
     * Room Related
     */

}