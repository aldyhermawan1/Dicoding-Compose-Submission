package com.hermawan.compose.moviedb.data.local.database

import androidx.room.RoomDatabase
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity

@androidx.room.Database(
    entities = [SeriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SeriesDatabase : RoomDatabase() {

    abstract fun seriesDao(): SeriesDao
}