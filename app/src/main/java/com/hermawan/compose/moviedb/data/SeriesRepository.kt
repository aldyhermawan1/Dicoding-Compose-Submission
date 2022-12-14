package com.hermawan.compose.moviedb.data

import com.hermawan.compose.moviedb.data.local.LocalDataSource
import com.hermawan.compose.moviedb.data.remote.RemoteDataSource
import com.hermawan.compose.moviedb.data.remote.api.ApiResponse
import com.hermawan.compose.moviedb.data.remote.model.DetailSeriesResponse
import com.hermawan.compose.moviedb.data.remote.model.SeriesResponse
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class SeriesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ISeriesRepository {

    override fun getPopular(): Flow<Resource<List<Series>>> {
        return object : NetworkBoundResource<List<Series>, List<SeriesResponse>>() {
            override suspend fun load(data: List<SeriesResponse>): Flow<List<Series>> {
                return listOf(data.map { it.toDomain() }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<SeriesResponse>>> {
                return remoteDataSource.getPopular()
            }
        }.asFlow()
    }

    override fun getDetail(id: Int): Flow<Resource<Series>> {
        return object : NetworkBoundResource<Series, DetailSeriesResponse>() {
            override suspend fun load(data: DetailSeriesResponse): Flow<Series> {
                return listOf(data.toDomain()).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailSeriesResponse>> {
                return remoteDataSource.getDetail(id)
            }
        }.asFlow()
    }

    override fun getSeries(query: String): Flow<Resource<List<Series>>> {
        return object : NetworkBoundResource<List<Series>, List<SeriesResponse>>() {
            override suspend fun load(data: List<SeriesResponse>): Flow<List<Series>> {
                return listOf(data.map { it.toDomain() }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<SeriesResponse>>> {
                return remoteDataSource.getSeries(query)
            }
        }.asFlow()
    }

    override suspend fun getFavorites(): Flow<List<Series>> {
        return localDataSource.getFavorites().map {
            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> {
        return localDataSource.isFavorite(id)
    }

    override suspend fun insertFavorite(series: Series) {
        localDataSource.insertFavorite(series.toEntity())
    }

    override suspend fun deleteFavorite(series: Series) {
        localDataSource.deleteFavorite(series.toEntity())
    }
}