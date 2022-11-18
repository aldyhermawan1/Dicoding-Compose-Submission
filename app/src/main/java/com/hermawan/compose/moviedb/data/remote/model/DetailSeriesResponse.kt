package com.hermawan.compose.moviedb.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hermawan.compose.moviedb.domain.model.Series
import com.hermawan.compose.moviedb.utils.Constants

data class DetailSeriesResponse(

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("last_air_date")
    val lastAirDate: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
) {

    fun toDomain(): Series {
        return Series(
            Constants.BASE_ORIGINAL_IMAGE_URL + backdropPath,
            firstAirDate,
            id,
            lastAirDate,
            name,
            numberOfEpisodes,
            overview,
            popularity,
            Constants.BASE_ORIGINAL_IMAGE_URL + posterPath,
            voteAverage
        )
    }
}
