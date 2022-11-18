package com.hermawan.compose.moviedb.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.ui.theme.DarkBlue

@Composable
fun TopBar(
    isBackVisible: Boolean,
    isAboutVisible: Boolean,
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        backgroundColor = DarkBlue,
        contentColor = Color.White,
        navigationIcon = {
            if (isBackVisible) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.action_back))
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(id = R.string.image_logo),
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        actions = {
            if (isAboutVisible) {
                IconButton(onClick = { onAboutClick() }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(id = R.string.title_about))
                }
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun TopBarPreview() {
    ComposeMovieDBTheme() {
        TopBar(isBackVisible = false, isAboutVisible = false, onBackClick = {}, onAboutClick = {})
    }
}