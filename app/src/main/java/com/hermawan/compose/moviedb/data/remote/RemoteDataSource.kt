package com.hermawan.compose.moviedb.data.remote

import com.hermawan.compose.moviedb.data.remote.api.ApiClient
import com.hermawan.compose.moviedb.data.remote.api.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getPopular() =
        flow {
            try {
                val response = apiClient.getPopular().data
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetail(id: Int) =
        flow {
            try {
                emit(ApiResponse.Success(apiClient.getDetail(id)))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getSeries(query: String) =
        flow {
            try {
                val response = apiClient.getSeries(query).data
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}