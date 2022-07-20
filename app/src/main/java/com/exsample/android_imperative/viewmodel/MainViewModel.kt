package com.exsample.android_imperative.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.model.TVShowDetails
import com.exsample.android_imperative.repository.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(private val repository: TVShowRepository): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsFromApi = MutableLiveData<ArrayList<TVShow>>()

    val valueTvShowPopular = MutableLiveData<TVShow>()
    val valueTvShowDetail = MutableLiveData<TVShowDetails>()

    /**
     * Retrofit Related
     */

    fun apiTvShowPopular(page: Int){

    }

    fun apiTvShowDetail(q: Int){

    }

    /**
     * Room Related
     */
}