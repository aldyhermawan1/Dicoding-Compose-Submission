package com.hermawan.compose.moviedb.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.IdlingRegistry
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.navigation.Screen
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.EspressoIdlingResource
import com.hermawan.compose.moviedb.utils.assertCurrentRouteName
import com.hermawan.compose.moviedb.utils.onNodeWithContentDescriptionStringId
import com.hermawan.compose.moviedb.utils.onNodeWithStringId
import org.junit.*

class SeriesAppTest {

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
    fun navHost_verifyStart() {
        navController.assertCurrentRouteName(Screen.Popular.route)
    }

    @Test
    fun navHost_navigateToDetail() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").performTextInput("Chainsaw Man")
            onNodeWithTag("SearchBar").performImeAction()
            onNodeWithTag("PopularItem").performClick()
            navController.assertCurrentRouteName(Screen.Detail.route)
        }
    }

    @Test
    fun navHost_navigateToAbout() {
        with(composeTestRule) {
            onNodeWithContentDescriptionStringId(R.string.page_about).performClick()
            navController.assertCurrentRouteName(Screen.About.route)
        }
    }

    @Test
    fun navHost_bottomNavigation() {
        with(composeTestRule) {
            onNodeWithStringId(R.string.title_favorite).performClick()
            navController.assertCurrentRouteName(Screen.Favorite.route)
            onNodeWithStringId(R.string.title_popular).performClick()
            navController.assertCurrentRouteName(Screen.Popular.route)
        }
    }

    @Test
    fun navHost_detail_navigatesBack() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").performTextInput("Chainsaw Man")
            onNodeWithTag("SearchBar").performImeAction()
            onNodeWithTag("PopularItem").performClick()
            navController.assertCurrentRouteName(Screen.Detail.route)
            onNodeWithTag("BackButton").assertExists().performClick()
            navController.assertCurrentRouteName(Screen.Popular.route)
        }
    }

    @Test
    fun navHost_about_navigatesBack() {
        with(composeTestRule) {
            onNodeWithContentDescriptionStringId(R.string.page_about).performClick()
            navController.assertCurrentRouteName(Screen.About.route)
            onNodeWithTag("BackButton").assertExists().performClick()
            navController.assertCurrentRouteName(Screen.Popular.route)
        }
    }
}