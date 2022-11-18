package com.hermawan.compose.moviedb.data.local.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface SeriesDao {

    @Query("SELECT * FROM series")
    fun getFavorites(): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM series WHERE id = :id")
    fun isFavorite(id: Int): Flow<List<SeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(seriesEntity: SeriesEntity)

    @Delete
    fun deleteFavorites(seriesEntity: SeriesEntity)
}