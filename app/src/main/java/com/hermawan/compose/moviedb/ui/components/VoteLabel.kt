package com.hermawan.compose.moviedb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.roundDecimal

@Composable
fun VoteLabel(
    vote: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = vote.roundDecimal().toString(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
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
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun VoteLabelPreview() {
    ComposeMovieDBTheme {
        VoteLabel(vote = 8.6)
    }
}