package com.hermawan.compose.moviedb.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hermawan.compose.moviedb.domain.model.Series

data class SeriesResponse(

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("popularity")
    val popularity: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("name")
    val name: String,
) {

    fun toDomain(): Series {
        return Series(
            posterPath = posterPath,
            popularity = popularity,
            id = id,
            backdropPath = backdropPath,
            voteAverage = voteAverage,
            overview = overview,
            firstAirDate = firstAirDate,
            name = name
        )
    }
}
