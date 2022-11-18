package com.hermawan.compose.moviedb.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.IdlingRegistry
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.EspressoIdlingResource
import com.hermawan.compose.moviedb.utils.onNodeWithStringId
import org.junit.*

class PopularScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        composeTestRule.setContent {
            ComposeMovieDBTheme {
                PopularScreen(onNavigateDetail = {})
            }
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun popularScreen_isDisplayed() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").assertIsDisplayed()
            onNodeWithTag("PopularList").performScrollToIndex(19)
        }
    }

    @Test
    fun popularScreen_search_successful() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").performTextInput("Chainsaw Man")
            onNodeWithTag("SearchBar").performImeAction()
            onNodeWithTag("PopularList").performScrollToIndex(0)
        }
    }

    @Test
    fun popularScreen_search_unsuccessful() {
        with(composeTestRule) {
            onNodeWithTag("SearchBar").performTextInput("ASDASDDBSASACASDJS")
            onNodeWithTag("SearchBar").performImeAction()
            onNodeWithTag("PopularList").assertDoesNotExist()
            onNodeWithStringId(R.string.message_empty_search).assertIsDisplayed()
        }
    }
}