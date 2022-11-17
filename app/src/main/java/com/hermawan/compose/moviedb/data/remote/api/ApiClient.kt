package com.hermawan.compose.moviedb.data.remote.api

import com.hermawan.compose.moviedb.data.remote.model.DetailSeriesResponse
import com.hermawan.compose.moviedb.data.remote.model.SeriesResponse
import com.hermawan.compose.moviedb.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("tv/top_rated/$API_KEY&page=1")
    suspend fun getTopRated(): List<SeriesResponse>

    @GET("tv/{id}/$API_KEY")
    suspend fun getDetail(@Path("id") id: Int): DetailSeriesResponse

    @GET("search/tv$API_KEY&page=1")
    suspend fun getSeries(@Query("query") query: String): List<SeriesResponse>
}