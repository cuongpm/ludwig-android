package com.ludwig.presentation.base

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by cuongpm on 5/11/19.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager?.beginTransaction()
            ?.replace(containerViewId, fragment)
            ?.commit()
    }
}
