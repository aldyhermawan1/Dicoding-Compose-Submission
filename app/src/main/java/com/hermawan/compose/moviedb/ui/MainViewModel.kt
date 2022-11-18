package com.hermawan.compose.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hermawan.compose.moviedb.domain.SeriesUseCase
import com.hermawan.compose.moviedb.domain.model.Series
import com.hermawan.compose.moviedb.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MainViewModel(private val useCase: SeriesUseCase) : ViewModel() {

    private val _topRated = MutableLiveData<ResultData<List<Series>>>()
    val topRated: LiveData<ResultData<List<Series>>> get() = _topRated

    private val _detail = MutableLiveData<ResultData<Series>>()
    val detail: LiveData<ResultData<Series>> get() = _detail

    private val _search = MutableLiveData<ResultData<List<Series>>>()
    val search: LiveData<ResultData<List<Series>>> get() = _search

    private val _favorites = MutableLiveData<ResultData<List<Series>>>()
    val favorites: LiveData<ResultData<List<Series>>> get() = _favorites

    private val _isFavorite = MutableLiveData<ResultData<Boolean>>()
    val isFavorite: LiveData<ResultData<Boolean>> get() = _isFavorite

    init {
        _topRated.value = ResultData.Default()
        _detail.value = ResultData.Default()
        _search.value = ResultData.Default()
        _favorites.value = ResultData.Default()
        _isFavorite.value = ResultData.Default()
    }

    fun getTopRated() {
        _topRated.value = ResultData.Loading()

        useCase.getTopRated().map {
            _topRated.value = if (it.isNotEmpty()) ResultData.Success(it) else ResultData.Empty()
        }.catch {
            _topRated.value = ResultData.Error(it.message)
        }
    }

    fun getDetail(id: Int) {
        _detail.value = ResultData.Loading()

        useCase.getDetail(id).map {
            _detail.value = ResultData.Success(it)
        }.catch {
            _detail.value = ResultData.Error(it.message)
        }.flowOn(Dispatchers.Main)
    }

    fun getSearch(query: String) {
        _search.value = ResultData.Loading()

        useCase.getSearch(query).map {
            _search.value = if (it.isNotEmpty()) ResultData.Success(it) else ResultData.Empty()
        }.catch {
            _search.value = ResultData.Error(it.message)
        }.flowOn(Dispatchers.Main)
    }

    fun getFavorites() {
        _favorites.value = ResultData.Loading()

        useCase.getFavorites().map {
            _favorites.value = if (it.isNotEmpty()) ResultData.Success(it) else ResultData.Empty()
        }.catch {
            _favorites.value = ResultData.Error(it.message)
        }.flowOn(Dispatchers.Main)
    }

    fun isFavorite(id: Int) {
        _isFavorite.value = ResultData.Loading()

        useCase.isFavorite(id).map {
            _isFavorite.value = ResultData.Success(it)
        }.catch {
            _isFavorite.value = ResultData.Error(it.message)
        }.flowOn(Dispatchers.Main)
    }

    fun insertFavorite(series: Series) {
        useCase.insertFavorite(series)
    }

    fun deleteFavorite(series: Series) {
        useCase.deleteFavorite(series)
    }
}