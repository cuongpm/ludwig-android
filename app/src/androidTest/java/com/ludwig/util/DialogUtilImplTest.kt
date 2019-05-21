package com.ludwig.util

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ludwig.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.TestFragmentActivity
import test.rule.InjectedActivityTestRule

class DialogUtilImplTest {

    private lateinit var dialogUtil: DialogUtil

    @get:Rule
    val activityTestRule = InjectedActivityTestRule(TestFragmentActivity::class.java) {}

    @Before
    fun setUp() {
        dialogUtil = DialogUtilImpl()
    }

    @Test
    fun showSimpleProgressBar_should_show_a_new_dialog_if_does_not_exist() {
        activityTestRule.launchActivity(null)

        activityTestRule.activity.runOnUiThread {
            dialogUtil.showSimpleProgressBar(activityTestRule.activity)
        }

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        activityTestRule.activity.runOnUiThread {
            dialogUtil.closeSimpleProgressBar()
        }
    }

    @Test
    fun showSimpleProgressBar_should_cancel_dialog_if_exists() {
        activityTestRule.launchActivity(null)

        activityTestRule.activity.runOnUiThread {
            dialogUtil.showSimpleProgressBar(activityTestRule.activity)
        }

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        activityTestRule.activity.runOnUiThread {
            dialogUtil.showSimpleProgressBar(activityTestRule.activity)
        }

        onView(withId(R.id.progress_bar)).check(doesNotExist())
    }

    @Test
    fun closeSimpleProgressBar_should_cancel_dialog_if_exists() {
        activityTestRule.launchActivity(null)

        activityTestRule.activity.runOnUiThread {
            dialogUtil.showSimpleProgressBar(activityTestRule.activity)
        }

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        activityTestRule.activity.runOnUiThread {
            dialogUtil.closeSimpleProgressBar()
        }

        onView(withId(R.id.progress_bar)).check(doesNotExist())
    }
}
