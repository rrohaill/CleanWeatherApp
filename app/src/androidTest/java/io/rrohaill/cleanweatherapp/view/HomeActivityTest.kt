package io.rrohaill.cleanweatherapp.view

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import io.rrohaill.cleanweatherapp.InstrumentTestHelper
import io.rrohaill.cleanweatherapp.view.home.HomeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var composeTestRule: ComposeContentTestRule = createAndroidComposeRule<HomeActivity>()

    @Before
    fun grantPermission() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.uiAutomation.executeShellCommand("pm grant ${instrumentation.targetContext.packageName} android.permission.ACCESS_COARSE_LOCATION")
    }

    @Test
    fun isHomeScreenContentDisplayed() {
        isProgressIndicatorDisplayed()
        isContentDisplayed()
        isImageDisplayed()
    }

    private fun isProgressIndicatorDisplayed() {
        composeTestRule.onNodeWithTag("myProgressIndicator").assertIsDisplayed()
            .assertRangeInfoEquals(ProgressBarRangeInfo.Indeterminate)

        Espresso.onView(ViewMatchers.isRoot()).perform(InstrumentTestHelper.waitFor(2000))
    }

    private fun isContentDisplayed() {
        composeTestRule.onNodeWithTag("myContent").assertIsDisplayed()
    }

    private fun isImageDisplayed() {
        composeTestRule.onNodeWithContentDescription("weather_image").assertExists()
    }
}