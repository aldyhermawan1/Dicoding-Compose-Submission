package com.hermawan.compose.moviedb.data

import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface ISeriesRepository {

    fun getPopular(): Flow<Resource<List<Series>>>
    fun getDetail(id: Int): Flow<Resource<Series>>
    fun getSeries(query: String): Flow<Resource<List<Series>>>

    suspend fun getFavorites(): Flow<List<Series>>
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun insertFavorite(series: Series)
    suspend fun deleteFavorite(series: Series)
}