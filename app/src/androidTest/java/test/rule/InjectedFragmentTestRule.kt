package test.rule

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.test.espresso.intent.rule.IntentsTestRule
import test.TestFragmentActivity

class InjectedFragmentTestRule<T : Fragment>(
    private val injector: (fragment: T) -> Unit,
    launchActivity: Boolean = true
) : IntentsTestRule<TestFragmentActivity>(TestFragmentActivity::class.java, false, launchActivity) {

    fun startActivity() {
        launchActivity(Intent())
    }

    @Suppress("UNCHECKED_CAST")
    fun attachFragment(fragment: T) {
        activity.attachFragment(fragment, injector)
    }
}
