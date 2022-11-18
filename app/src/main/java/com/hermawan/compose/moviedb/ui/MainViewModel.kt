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
import com.hermawan.compose.moviedb.utils.EspressoIdlingResource
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
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.getPopular().collect {
                _series.value = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun getDetail(id: Int) {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.getDetail(id).collect {
                _detailSeries.value = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun getSearch(query: String) {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.getSearch(query).collect {
                _series.value = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun getFavorites() {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.getFavorites().collect {
                favoriteSeries = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun checkFavorite(id: Int) {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.isFavorite(id).collect {
                isFavorite = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun insertFavorite(series: Series) {
        EspressoIdlingResource.increment()
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertFavorite(series)
            EspressoIdlingResource.decrement()
        }
    }

    fun deleteFavorite(series: Series) {
        EspressoIdlingResource.increment()
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavorite(series)
            EspressoIdlingResource.decrement()
        }
    }
}