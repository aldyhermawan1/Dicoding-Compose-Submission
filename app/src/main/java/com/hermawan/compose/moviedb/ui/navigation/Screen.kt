package com.hermawan.compose.moviedb.ui.navigation

sealed class Screen(val route: String) {
    object Popular : Screen("popular")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("detail/{seriesId}") {

        fun createRoute(seriesId: Int) = "detail/$seriesId"
    }
}
