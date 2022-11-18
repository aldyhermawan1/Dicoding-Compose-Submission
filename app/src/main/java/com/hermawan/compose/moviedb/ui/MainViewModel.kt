package com.hermawan.compose.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermawan.compose.moviedb.data.Resource
import com.hermawan.compose.moviedb.domain.SeriesUseCase
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: SeriesUseCase) : ViewModel() {

    private val _series = MutableLiveData<Resource<List<Series>>>()
    val series: LiveData<Resource<List<Series>>> get() = _series

    private val _detailSeries = MutableLiveData<Resource<Series>>()
    val detailSeries: LiveData<Resource<Series>> get() = _detailSeries

    private val _favoriteSeries = MutableLiveData<List<Series>>()
    val favoriteSeries: LiveData<List<Series>> get() = _favoriteSeries

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

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
                _favoriteSeries.value = it
            }
        }
    }

    fun checkFavorite(id: Int) {
        viewModelScope.launch {
            useCase.isFavorite(id).collect {
                _isFavorite.value = it
            }
        }
    }

    fun insertFavorite(series: Series) {
        viewModelScope.launch {
            useCase.insertFavorite(series)
        }
    }

    fun deleteFavorite(series: Series) {
        viewModelScope.launch {
            useCase.deleteFavorite(series)
        }
    }
}