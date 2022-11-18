package com.hermawan.compose.moviedb.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hermawan.compose.moviedb.R.drawable
import com.hermawan.compose.moviedb.R.string
import com.hermawan.compose.moviedb.domain.model.Series
import com.hermawan.compose.moviedb.ui.MainViewModel
import com.hermawan.compose.moviedb.ui.components.GenericState
import com.hermawan.compose.moviedb.ui.components.SeriesItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onNavigateDetail: (Int) -> Unit,
) {
    viewModel.getFavorites()
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when {
            viewModel.favoriteSeries.isEmpty() -> {
                GenericState(message = stringResource(id = string.message_empty_favorite), drawable = drawable.img_empty)
            }
            viewModel.favoriteSeries.isNotEmpty() -> {
                FavoriteContent(favoriteSeries = viewModel.favoriteSeries, onNavigateDetail = onNavigateDetail)
            }
        }
    }
}

@Composable
fun FavoriteContent(
    favoriteSeries: List<Series>,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("FavoriteList")
    ) {
        items(favoriteSeries) {
            SeriesItem(
                seriesId = it.id,
                posterPath = it.posterPath ?: "",
                name = it.name,
                firstAirDate = it.firstAirDate ?: "",
                vote = it.voteAverage,
                onClickListener = { id ->
                    onNavigateDetail(id)
                }
            )
        }
    }
}