package com.hermawan.compose.moviedb.domain

import com.hermawan.compose.moviedb.data.Resource
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesUseCase {

    fun getPopular(): Flow<Resource<List<Series>>>
    fun getDetail(id: Int): Flow<Resource<Series>>
    fun getSearch(query: String): Flow<Resource<List<Series>>>

    fun getFavorites(): Flow<List<Series>>
    fun isFavorite(id: Int): Flow<Boolean>
    fun insertFavorite(series: Series)
    fun deleteFavorite(series: Series)
}