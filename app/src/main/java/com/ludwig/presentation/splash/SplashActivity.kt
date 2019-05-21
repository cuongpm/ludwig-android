package com.ludwig.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.ludwig.R
import com.ludwig.presentation.base.BaseActivity
import com.ludwig.presentation.home.MainActivity
import com.ludwig.util.IntentUtil
import javax.inject.Inject

/**
 * Created by cuongpm on 5/11/19.
 */

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var intentUtil: IntentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            intentUtil.startActivity(this, MainActivity::class.java)
            finish()
        }, 3000)
    }
}
