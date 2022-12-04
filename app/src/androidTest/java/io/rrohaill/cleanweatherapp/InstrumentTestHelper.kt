package io.rrohaill.cleanweatherapp

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class InstrumentTestHelper {
    companion object {

        /**
         * Perform action of waiting for a specific time.
         * @param time amount of time to wait in Milliseconds
         * @return ViewAction
         */
        fun waitFor(time: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isRoot()
                }

                override fun getDescription(): String {
                    return "Waiting for $time milliseconds"
                }

                override fun perform(uiController: UiController, view: View?) {
                    uiController.loopMainThreadForAtLeast(time.toLong())
                }
            }
        }

    }
}