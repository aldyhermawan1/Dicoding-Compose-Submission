package com.hermawan.compose.moviedb.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.IdlingRegistry
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.SeriesApp
import com.hermawan.compose.moviedb.ui.navigation.Screen
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.EspressoIdlingResource
import com.hermawan.compose.moviedb.utils.assertCurrentRouteName
import com.hermawan.compose.moviedb.utils.onNodeWithStringId

import org.junit.*

class FavoriteScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        composeTestRule.setContent {
            ComposeMovieDBTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                SeriesApp(navController = navController)
            }
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun favoriteScreen_empty() {
        with(composeTestRule) {
            onNodeWithStringId(R.string.title_favorite).performClick()
            navController.assertCurrentRouteName(Screen.Favorite.route)
            onNodeWithStringId(R.string.message_empty_favorite).assertIsDisplayed()
        }
    }

    @Test
    fun favoriteScreen_displayed() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").performTextInput("Chainsaw Man")
            onNodeWithTag("SearchBar").performImeAction()
            onNodeWithTag("PopularList").performScrollToIndex(0)
            onNodeWithTag("PopularItem").performClick()
            navController.assertCurrentRouteName(Screen.Detail.route)
            onNodeWithTag("FavoriteButton").performClick()
            onNodeWithTag("BackButton").performClick()
            navController.assertCurrentRouteName(Screen.Popular.route)
            onNodeWithStringId(R.string.title_favorite).performClick()
            navController.assertCurrentRouteName(Screen.Favorite.route)
            onNodeWithTag("FavoriteList").performScrollToIndex(0)
            onNodeWithTag("FavoriteItem").performClick()
            navController.assertCurrentRouteName(Screen.Detail.route)
            onNodeWithTag("FavoriteButton").performClick()
        }
    }
}