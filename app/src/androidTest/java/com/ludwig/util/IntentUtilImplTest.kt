package com.ludwig.util

import android.app.Activity
import android.app.Instrumentation
import android.os.Bundle
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.BundleMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtras
import com.ludwig.presentation.home.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import test.TestFragmentActivity
import test.rule.InjectedActivityTestRule

class IntentUtilImplTest {

    private val intentUtil = IntentUtilImpl()

    @get:Rule
    val activityTestRule = InjectedActivityTestRule(TestFragmentActivity::class.java) {}

    @Test
    fun startActivity() {
        activityTestRule.launchActivity(null)

        intending(hasComponent(MainActivity::class.java.name)).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
        )

        intentUtil.startActivity(activityTestRule.activity, MainActivity::class.java)

        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun startActivity_has_bundle() {
        activityTestRule.launchActivity(null)

        intending(hasComponent(MainActivity::class.java.name)).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
        )

        val bundle = Bundle().apply {
            putInt("TEST_KEY", 123)
        }

        intentUtil.startActivity(activityTestRule.activity, MainActivity::class.java, bundle)

        intended(
            allOf(
                hasComponent(MainActivity::class.java.name),
                hasExtras(BundleMatchers.hasEntry("TEST_KEY", 123))
            )
        )
    }
}

