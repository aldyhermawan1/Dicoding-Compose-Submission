package com.hermawan.compose.moviedb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.changeDateFormat

@Composable
fun SeriesItem(
    seriesId: Int,
    posterPath: String,
    name: String,
    firstAirDate: String,
    vote: Double,
    onClickListener: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .shadow(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClickListener(seriesId) }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = posterPath,
                    contentDescription = name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = vote.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(
                                color = Color.DarkGray,
                                shape = CircleShape
                            )
                            .border(
                                width = 2.dp,
                                color = when {
                                    vote <= 3.3 -> Color.Red
                                    vote in 3.3..6.6 -> Color.Yellow
                                    vote >= 6.6 -> Color.Green
                                    else -> Color.Black
                                },
                                shape = CircleShape
                            )
                            .padding(6.dp)
                    )
                }
            }
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            )
            Text(
                text = changeDateFormat(firstAirDate, "yyyy-MM-dd", "MMM dd, yyyy"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeriesItemPreview() {
    ComposeMovieDBTheme {
        SeriesItem(
            seriesId = 114410,
            posterPath = "",
            name = "Chainsaw Man",
            firstAirDate = "2022-10-12",
            vote = 8.6,
            onClickListener = { }
        )
    }
}