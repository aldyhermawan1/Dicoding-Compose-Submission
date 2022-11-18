package com.hermawan.compose.moviedb.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermawan.compose.moviedb.data.Resource
import com.hermawan.compose.moviedb.domain.SeriesUseCase
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: SeriesUseCase) : ViewModel() {

    private val _series = MutableLiveData<Resource<List<Series>>>()
    val series: LiveData<Resource<List<Series>>> get() = _series

    private val _detailSeries = MutableLiveData<Resource<Series>>()
    val detailSeries: LiveData<Resource<Series>> get() = _detailSeries

    var favoriteSeries by mutableStateOf(emptyList<Series>())

    var isFavorite by mutableStateOf(false)

    fun getPopular() {
        viewModelScope.launch {
            useCase.getPopular().collect {
                _series.value = it
            }
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            useCase.getDetail(id).collect {
                _detailSeries.value = it
            }
        }
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            useCase.getSearch(query).collect {
                _series.value = it
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            useCase.getFavorites().collect {
                favoriteSeries = it
            }
        }
    }

    fun checkFavorite(id: Int) {
        viewModelScope.launch {
            useCase.isFavorite(id).collect {
                isFavorite = it
            }
        }
    }

    fun insertFavorite(series: Series) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertFavorite(series)
        }
    }

    fun deleteFavorite(series: Series) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavorite(series)
        }
    }
}