package com.hermawan.compose.moviedb.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hermawan.compose.moviedb.domain.model.Series

@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Int,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "name")
    val name: String,
) {
    fun toDomain(): Series {
        return Series(
            id = id,
            posterPath = posterPath,
            popularity = popularity,
            backdropPath = backdropPath,
            voteAverage = voteAverage,
            overview = overview,
            firstAirDate = firstAirDate,
            name = name
        )
    }
}
