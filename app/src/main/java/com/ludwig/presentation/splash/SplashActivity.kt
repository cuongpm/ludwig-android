package com.ludwig.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.ludwig.R
import com.ludwig.presentation.base.BaseActivity
import com.ludwig.presentation.home.MainActivity

/**
 * Created by cuongpm on 5/11/19.
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }
}