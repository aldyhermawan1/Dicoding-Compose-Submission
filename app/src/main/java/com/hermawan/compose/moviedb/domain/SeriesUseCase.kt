package com.hermawan.compose.moviedb.domain

import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesUseCase {

    fun getTopRated(): Flow<List<Series>>
    fun getDetail(id: Int): Flow<Series>
    fun getSearch(query: String): Flow<List<Series>>

    fun getFavorites(): Flow<List<Series>>
    fun isFavorite(id: Int): Flow<Boolean>
    fun insertFavorite(series: Series)
    fun deleteFavorite(series: Series)
}