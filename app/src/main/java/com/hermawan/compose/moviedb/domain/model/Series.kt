package com.hermawan.compose.moviedb.domain.model

import android.os.Parcelable
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(

    val backdropPath: String?,
    val firstAirDate: String?,
    val id: Int,
    val lastAirDate: String? = null,
    val name: String,
    val numberOfEpisodes: Int? = null,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
) : Parcelable {

    fun toEntity(): SeriesEntity {
        return SeriesEntity(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            id = id,
            name = name,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            voteAverage = voteAverage
        )
    }
}
