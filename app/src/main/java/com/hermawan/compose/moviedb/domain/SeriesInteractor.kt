package com.hermawan.compose.moviedb.domain

import com.hermawan.compose.moviedb.data.ISeriesRepository
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SeriesInteractor(private val seriesRepository: ISeriesRepository) : SeriesUseCase {

    override fun getTopRated(): Flow<List<Series>> {
        return seriesRepository.getTopRated().map {
            it.map { response ->
                response.toDomain()
            }
        }
    }

    override fun getDetail(id: Int): Flow<Series> {
        return seriesRepository.getDetail(id).map {
            it.toDomain()
        }
    }

    override fun getSearch(query: String): Flow<List<Series>> {
        return seriesRepository.getSeries(query).map {
            it.map { response ->
                response.toDomain()
            }
        }
    }

    override fun getFavorites(): Flow<List<Series>> {
        return seriesRepository.getFavorites().map {
            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return seriesRepository.isFavorite(id)
    }

    override fun insertFavorite(series: Series) {
        return seriesRepository.insertFavorite(series.toEntity())
    }

    override fun deleteFavorite(series: Series) {
        return seriesRepository.deleteFavorite(series.toEntity())
    }
}