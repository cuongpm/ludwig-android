package com.ludwig.presentation.home

import android.os.Bundle
import com.ludwig.R
import com.ludwig.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.fragment_content, HomeFragment.newInstance())
    }
}
