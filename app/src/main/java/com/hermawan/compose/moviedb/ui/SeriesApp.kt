package com.hermawan.compose.moviedb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.components.BottomBar
import com.hermawan.compose.moviedb.ui.navigation.Screen
import com.hermawan.compose.moviedb.ui.screen.AboutScreen
import com.hermawan.compose.moviedb.ui.screen.DetailScreen
import com.hermawan.compose.moviedb.ui.screen.FavoriteScreen
import com.hermawan.compose.moviedb.ui.screen.PopularScreen
import com.hermawan.compose.moviedb.ui.theme.DarkBlue

@Composable
fun SeriesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = DarkBlue,
                contentColor = Color.White
            )
        },
        bottomBar = {
            if (currentRoute != Screen.Detail.route || currentRoute != Screen.About.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Popular.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Popular.route) {
                PopularScreen(onNavigateDetail = {
                    navController.navigate(Screen.Detail.createRoute(it))
                })
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(Screen.Detail.route) {
                DetailScreen()
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}