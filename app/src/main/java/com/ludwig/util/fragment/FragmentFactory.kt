package com.ludwig.util.fragment

import androidx.fragment.app.Fragment
import com.ludwig.presentation.home.HomeFragment
import javax.inject.Inject

/**
 * Created by cuongpm on 2019-05-21.
 */

interface FragmentFactory {
    fun createHomeFragment(): Fragment
}

class FragmentFactoryImpl @Inject constructor() : FragmentFactory {

    override fun createHomeFragment() = HomeFragment.newInstance()
}