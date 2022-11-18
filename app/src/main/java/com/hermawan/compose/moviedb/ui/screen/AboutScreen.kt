package com.hermawan.compose.moviedb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_about),
            contentDescription = stringResource(id = R.string.image_about),
            modifier = Modifier
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                .size(250.dp)
                .align(CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.content_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.content_email),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    ComposeMovieDBTheme {
        AboutScreen()
    }
}