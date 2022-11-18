package com.hermawan.compose.moviedb.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.data.Resource.Empty
import com.hermawan.compose.moviedb.data.Resource.Error
import com.hermawan.compose.moviedb.data.Resource.Loading
import com.hermawan.compose.moviedb.data.Resource.Success
import com.hermawan.compose.moviedb.domain.model.Series
import com.hermawan.compose.moviedb.ui.MainViewModel
import com.hermawan.compose.moviedb.ui.components.GenericState
import com.hermawan.compose.moviedb.ui.components.VoteLabel
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.ui.theme.DarkBlue
import com.hermawan.compose.moviedb.utils.changeDateFormat
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun DetailScreen(
    seriesId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
) {
    val state by viewModel.detailSeries.observeAsState()

    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        when (state) {
            is Empty -> {
                GenericState(message = stringResource(id = R.string.message_error), drawable = R.drawable.img_error)
            }
            is Error -> {
                GenericState(message = state?.message.toString(), drawable = R.drawable.img_error)
            }
            is Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is Success -> {
                DetailContent(detailSeries = state?.data!!, navigateBack = navigateBack)
            }
            else -> {
                viewModel.getDetail(seriesId)
            }
        }
    }
}

@Composable
fun DetailContent(
    detailSeries: Series,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
) {
    val isFavorite by viewModel.isFavorite.observeAsState()
    when (isFavorite) {
        true -> Timber.d("TRUE")
        false -> Timber.d("FALSE")
        null -> {
            Timber.d("NULL")
            viewModel.checkFavorite(detailSeries.id)
        }
    }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = detailSeries.backdropPath,
                contentDescription = detailSeries.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            FloatingActionButton(
                onClick = { navigateBack() },
                backgroundColor = Color.White,
                contentColor = DarkBlue,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.action_back))
            }
            FloatingActionButton(
                onClick = {
                    if (isFavorite == true) {
                        viewModel.deleteFavorite(detailSeries)
                    } else {
                        viewModel.insertFavorite(detailSeries)
                    }
                    viewModel.checkFavorite(detailSeries.id)
                },
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.action_back)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = detailSeries.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
            VoteLabel(
                vote = detailSeries.voteAverage,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
        }
        Column(Modifier.padding(16.dp)) {
            Text(
                text = detailSeries.overview,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.label_air_date),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(
                    id = R.string.content_air_date,
                    changeDateFormat(detailSeries.firstAirDate.toString(), "yyyy-MM-dd", "MMMM yyyy"),
                    changeDateFormat(detailSeries.lastAirDate.toString(), "yyyy-MM-dd", "MMMM yyyy")
                ),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.label_episodes),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = detailSeries.numberOfEpisodes.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    ComposeMovieDBTheme {
        DetailContent(
            Series(
                "",
                "2022-10-12",
                114410,
                "2022-11-16",
                "Chainsaw Man",
                12,
                "Denji has a simple dream—to live a happy and peaceful life, spending time with a girl he likes. This is a far cry from reality, however, as Denji is forced by the yakuza into killing devils in order to pay off his crushing debts. Using his pet devil Pochita as a weapon, he is ready to do anything for a bit of cash.",
                1304.295,
                "",
                8.631
            ), {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ComposeMovieDBTheme {
        DetailScreen(seriesId = 114410, navigateBack = { })
    }
}