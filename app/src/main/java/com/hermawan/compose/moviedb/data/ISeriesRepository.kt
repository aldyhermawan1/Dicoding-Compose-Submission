package com.hermawan.compose.moviedb.data

import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import com.hermawan.compose.moviedb.data.remote.model.DetailSeriesResponse
import com.hermawan.compose.moviedb.data.remote.model.SeriesResponse
import kotlinx.coroutines.flow.Flow

interface ISeriesRepository {

    fun getTopRated(): Flow<List<SeriesResponse>>
    fun getDetail(id: Int): Flow<DetailSeriesResponse>
    fun getSeries(query: String): Flow<List<SeriesResponse>>

    fun getFavorites(): Flow<List<SeriesEntity>>
    fun isFavorite(id: Int): Flow<Boolean>
    fun insertFavorite(series: SeriesEntity)
    fun deleteFavorite(series: SeriesEntity)
}