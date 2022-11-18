package com.hermawan.compose.moviedb.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hermawan.compose.moviedb.R
import com.hermawan.compose.moviedb.ui.theme.ComposeMovieDBTheme
import com.hermawan.compose.moviedb.utils.onNodeWithContentDescriptionStringId
import com.hermawan.compose.moviedb.utils.onNodeWithStringId
import org.junit.*

class AboutScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeMovieDBTheme {
                AboutScreen()
            }
        }
    }

    @Test
    fun aboutScreen_isDisplayed() {
        with(composeTestRule) {
            onNodeWithContentDescriptionStringId(R.string.image_about).assertIsDisplayed()
            onNodeWithStringId(R.string.content_email).assertIsDisplayed()
            onNodeWithStringId(R.string.content_name).assertIsDisplayed()
        }
    }
}