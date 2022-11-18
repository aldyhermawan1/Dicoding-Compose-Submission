package com.hermawan.compose.moviedb.data.local.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity

@androidx.room.Dao
interface SeriesDao {

    @Query("SELECT * FROM series")
    suspend fun getFavorites(): List<SeriesEntity>

    @Query("SELECT * FROM series WHERE id = :id")
    suspend fun isFavorite(id: Int): List<SeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(seriesEntity: SeriesEntity)

    @Delete
    suspend fun deleteFavorites(seriesEntity: SeriesEntity)
}