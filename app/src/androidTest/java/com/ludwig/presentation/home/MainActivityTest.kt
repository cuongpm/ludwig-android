package com.ludwig.presentation.home

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ludwig.R
import com.ludwig.util.fragment.FragmentFactory
import com.ludwig.util.fragment.StubbedFragmentFactory
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import test.rule.InjectedActivityTestRule

class MainActivityTest {

    private val fragmentFactory: FragmentFactory = StubbedFragmentFactory()

    private val screen = Screen()

    @get:Rule
    val uiRule = InjectedActivityTestRule(MainActivity::class.java) {
        it.fragmentFactory = fragmentFactory
    }

    @Test
    fun display_home_screen() {
        screen.start()
        screen.verifyToolbar()
    }

    @Test
    fun display_navigation_drawer_when_click_on_home_button() {
        screen.start()

        screen.clickHomeButton()
        screen.verifyNavigationDrawer(true)

        screen.clickHomeMenuOnDrawer()
        screen.verifyNavigationDrawer(false)
    }

    inner class Screen {

        fun start() {
            uiRule.launchActivity(Intent())
        }

        fun verifyToolbar() {
            onView(
                allOf(
                    isAssignableFrom(TextView::class.java),
                    withParent(isAssignableFrom(Toolbar::class.java))
                )
            ).check(matches(withText(R.string.home)))
        }

        fun clickHomeButton() {
            onView(withContentDescription("Navigate up")).perform(click())
        }

        fun clickHomeMenuOnDrawer() {
            onView(withId(R.id.tv_home)).perform(click())
        }

        fun verifyNavigationDrawer(shown: Boolean) {
            onView(withId(R.id.nav_drawer)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.layout_profile)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_home)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_premium)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_history)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_contact)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_business)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_about)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_how_to_use)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_blog)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
            onView(withId(R.id.tv_press_kit)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
        }
    }
}
