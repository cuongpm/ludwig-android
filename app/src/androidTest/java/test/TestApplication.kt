package test

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

class TestApplication : Application(), HasActivityInjector {

    var activityInjector: AndroidInjector<Activity> = AndroidInjector {}

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override fun activityInjector() = activityInjector

    companion object {
        lateinit var INSTANCE: TestApplication
    }
}
