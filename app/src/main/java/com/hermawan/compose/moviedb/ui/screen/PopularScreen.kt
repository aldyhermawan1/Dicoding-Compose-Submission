package com.hermawan.compose.moviedb.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.data.Resource
import com.hermawan.compose.moviedb.domain.model.Series
import com.hermawan.compose.moviedb.ui.MainViewModel
import com.hermawan.compose.moviedb.ui.components.GenericState
import com.hermawan.compose.moviedb.ui.components.SearchBar
import com.hermawan.compose.moviedb.ui.components.SeriesItem
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PopularScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onNavigateDetail: (Int) -> Unit,
) {
    val state by viewModel.series.observeAsState()

    Column(modifier = modifier) {
        SearchBar(
            onSearch = {
                if (it.isNotEmpty()) viewModel.getSearch(it) else viewModel.getPopular()
            },
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (state) {
                is Resource.Empty -> {
                    GenericState(message = stringResource(id = R.string.message_empty_search), drawable = R.drawable.img_empty)
                }
                is Resource.Error -> {
                    GenericState(message = state?.message.toString(), drawable = R.drawable.img_error)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Resource.Success -> {
                    PopularContent(popularSeries = state?.data!!, onNavigateDetail = onNavigateDetail)
                }
                else -> {
                    viewModel.getPopular()
                }
            }
        }
    }
}

@Composable
fun PopularContent(
    popularSeries: List<Series>,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("PopularList")
    ) {
        items(popularSeries) {
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

@Preview(showBackground = true)
@Composable
fun PopularScreenPreview() {
    ComposeMovieDBTheme {
        PopularScreen(onNavigateDetail = {})
    }
}