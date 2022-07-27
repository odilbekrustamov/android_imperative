package com.exsample.android_imperative.ui.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.repository.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
@Inject constructor(
    private val repository: TVShowRepository
    ): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    val tvShowsFromDB = MutableLiveData<List<TVShow>>()

    fun deleteTVShowsFromDB(){
        viewModelScope.launch {
            repository.deleteTvShowsFromDB()
        }
    }
    fun getTVSowFromDB(){
        viewModelScope.launch {
            val tvShows = repository.getTVShowsFromDB()
            tvShowsFromDB.postValue(tvShows)
        }
    }

}