package com.hermawan.compose.moviedb.domain

import com.hermawan.compose.moviedb.data.ISeriesRepository
import com.hermawan.compose.moviedb.data.Resource
import com.hermawan.compose.moviedb.domain.model.Series
import kotlinx.coroutines.flow.Flow

class SeriesInteractor(private val seriesRepository: ISeriesRepository) : SeriesUseCase {

    override fun getPopular(): Flow<Resource<List<Series>>> {
        return seriesRepository.getPopular()
    }

    override fun getDetail(id: Int): Flow<Resource<Series>> {
        return seriesRepository.getDetail(id)
    }

    override fun getSearch(query: String): Flow<Resource<List<Series>>> {
        return seriesRepository.getSeries(query)
    }

    override fun getFavorites(): Flow<List<Series>> {
        return seriesRepository.getFavorites()
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return seriesRepository.isFavorite(id)
    }

    override fun insertFavorite(series: Series) {
        return seriesRepository.insertFavorite(series)
    }

    override fun deleteFavorite(series: Series) {
        return seriesRepository.deleteFavorite(series)
    }
}