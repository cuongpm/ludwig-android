package test

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
class TestFragmentActivity : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var fragmentInjector: AndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    fun attachFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        runBlockingOnUiThread {
            this.fragmentInjector = injector
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commitNow()
        }
    }

    inline fun <T : Fragment> attachFragment(fragment: T, crossinline injector: (T) -> Unit) {
        attachFragment(fragment, AndroidInjector { injector(it as T) })
    }

    private fun runBlockingOnUiThread(block: () -> Unit) {
        val latch = CountDownLatch(1)
        runOnUiThread {
            block()
            latch.countDown()
        }
        latch.await(200, TimeUnit.MILLISECONDS)
    }
}
