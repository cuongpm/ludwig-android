package test.rule

import android.app.Activity
import androidx.test.espresso.intent.rule.IntentsTestRule
import dagger.android.AndroidInjector
import test.TestApplication

class InjectedActivityTestRule<T : Activity>(
    clazz: Class<T>,
    launchActivity: Boolean = false,
    private val injector: (activity: T) -> Unit
) : IntentsTestRule<T>(clazz, true, launchActivity) {

    @Suppress("UNCHECKED_CAST")
    override fun beforeActivityLaunched() {
        TestApplication.INSTANCE.activityInjector = AndroidInjector {
            try {
                injector(it as T)
            } catch (e: Exception) {
            }
        }
    }
}
