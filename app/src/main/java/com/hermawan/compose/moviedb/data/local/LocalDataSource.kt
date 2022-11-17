package com.hermawan.compose.moviedb.data.local

import com.hermawan.compose.moviedb.data.local.database.SeriesDao
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSource(private val seriesDao: SeriesDao) {

    fun getFavorites(): Flow<List<SeriesEntity>> {
        return seriesDao.getFavorites()
    }

    fun isFavorite(id: Int): Flow<Boolean> {
        return flow { seriesDao.isFavorite(id).isNotEmpty() }
    }

    fun insertFavorite(seriesEntity: SeriesEntity) {
        seriesDao.insertFavorites(seriesEntity)
    }

    fun deleteFavorite(seriesEntity: SeriesEntity) {
        seriesDao.deleteFavorites(seriesEntity)
    }
}