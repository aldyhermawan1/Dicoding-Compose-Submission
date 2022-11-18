package com.hermawan.compose.moviedb.data.local

import com.hermawan.compose.moviedb.data.local.database.SeriesDao
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSource(private val seriesDao: SeriesDao) {

    suspend fun getFavorites(): Flow<List<SeriesEntity>> {
        return flow { seriesDao.getFavorites() }
    }

    suspend fun isFavorite(id: Int): Flow<Boolean> {
        return flow { seriesDao.isFavorite(id).isNotEmpty() }
    }

    suspend fun insertFavorite(seriesEntity: SeriesEntity) {
        seriesDao.insertFavorites(seriesEntity)
    }

    suspend fun deleteFavorite(seriesEntity: SeriesEntity) {
        seriesDao.deleteFavorites(seriesEntity)
    }
}