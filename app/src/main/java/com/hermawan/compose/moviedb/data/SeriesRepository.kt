package com.hermawan.compose.moviedb.data

import android.os.Handler
import android.os.Looper
import com.hermawan.compose.moviedb.data.local.LocalDataSource
import com.hermawan.compose.moviedb.data.local.model.SeriesEntity
import com.hermawan.compose.moviedb.data.remote.RemoteDataSource
import com.hermawan.compose.moviedb.data.remote.model.DetailSeriesResponse
import com.hermawan.compose.moviedb.data.remote.model.SeriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SeriesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ISeriesRepository {

    override fun getTopRated(): Flow<List<SeriesResponse>> {
        return flow {
            remoteDataSource.getTopRated()
        }
    }

    override fun getDetail(id: Int): Flow<DetailSeriesResponse> {
        return flow {
            remoteDataSource.getDetail(id)
        }
    }

    override fun getSeries(query: String): Flow<List<SeriesResponse>> {
        return flow {
            remoteDataSource.getSeries(query)
        }
    }

    override fun getFavorites(): Flow<List<SeriesEntity>> {
        return flow {
            localDataSource.getFavorites()
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return flow {
            localDataSource.isFavorite(id)
        }
    }

    override fun insertFavorite(series: SeriesEntity) {
        Handler(Looper.getMainLooper()).post {
            localDataSource.insertFavorite(series)
        }
    }

    override fun deleteFavorite(series: SeriesEntity) {
        Handler(Looper.getMainLooper()).post {
            localDataSource.deleteFavorite(series)
        }
    }
}