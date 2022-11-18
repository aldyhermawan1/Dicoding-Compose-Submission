package com.hermawan.compose.moviedb.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.IdlingRegistry
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.EspressoIdlingResource
import com.hermawan.compose.moviedb.utils.onNodeWithStringId
import org.junit.*

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun detailScreen_isDisplayed() {
        composeTestRule.setContent {
            ComposeMovieDBTheme {
                DetailScreen(seriesId = 114410, navigateBack = { })
            }
        }
        with(composeTestRule) {
            onNodeWithText("Chainsaw Man").assertIsDisplayed()
        }
    }

    @Test
    fun detailScreen_isNotDisplayed() {
        composeTestRule.setContent {
            ComposeMovieDBTheme {
                DetailScreen(seriesId = 1231231231, navigateBack = { })
            }
        }
        with(composeTestRule) {
            onNodeWithText("Chainsaw Man").assertDoesNotExist()
            onNodeWithStringId(R.string.message_error).assertIsDisplayed()
        }
    }
}