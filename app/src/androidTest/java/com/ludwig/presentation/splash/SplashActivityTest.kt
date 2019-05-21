package com.ludwig.presentation.splash

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ludwig.R
import com.ludwig.presentation.home.MainActivity
import com.ludwig.util.IntentUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.strytelr.com.strytelr.test.util.TestUtil.waitUntil
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import test.rule.InjectedActivityTestRule
import java.util.concurrent.Callable

class SplashActivityTest {

    private val intentUtil: IntentUtil = mock()

    private val screen = Screen()

    @get:Rule
    val uiRule = InjectedActivityTestRule(SplashActivity::class.java) {
        it.intentUtil = intentUtil
    }

    @Test
    fun initial_ui() {
        screen.start()
        screen.verifySplashScreenContent()

        waitUntil("Wait for opening MainActivity", Callable {
            verify(intentUtil).startActivity(uiRule.activity, MainActivity::class.java)
            assertTrue(uiRule.activity.isFinishing)
            true
        }, 3000)
    }

    inner class Screen {

        fun start() {
            uiRule.launchActivity(Intent())
        }

        fun verifySplashScreenContent() {
            onView(withId(R.id.iv_logo)).check(matches(isDisplayed()))
        }
    }
}
