package com.hermawan.compose.moviedb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hermawan.compose.moviedb.ui.components.BottomBar
import com.hermawan.compose.moviedb.ui.components.TopBar
import com.hermawan.compose.moviedb.ui.navigation.Screen
import com.hermawan.compose.moviedb.ui.screen.AboutScreen
import com.hermawan.compose.moviedb.ui.screen.DetailScreen
import com.hermawan.compose.moviedb.ui.screen.FavoriteScreen
import com.hermawan.compose.moviedb.ui.screen.PopularScreen

@Composable
fun SeriesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Detail.route) {
                TopBar(
                    isBackVisible = currentRoute == Screen.About.route,
                    isAboutVisible = currentRoute != Screen.About.route,
                    onBackClick = { navController.navigateUp() },
                    onAboutClick = { navController.navigate(Screen.About.route) }
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Detail.route && currentRoute != Screen.About.route) {
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
                FavoriteScreen(onNavigateDetail = {
                    navController.navigate(Screen.Detail.createRoute(it))
                })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("seriesId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("seriesId") ?: -1
                DetailScreen(
                    seriesId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}