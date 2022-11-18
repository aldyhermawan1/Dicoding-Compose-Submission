package com.hermawan.compose.moviedb.data.remote

import com.hermawan.compose.moviedb.data.remote.api.ApiClient

class RemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getTopRated() = apiClient.getTopRated().data

    suspend fun getDetail(id: Int) = apiClient.getDetail(id)

    suspend fun getSeries(query: String) = apiClient.getSeries(query).data
}