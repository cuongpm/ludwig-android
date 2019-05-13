package com.ludwig.di.module

import android.app.Application
import android.content.Context
import com.ludwig.LudwigApplication
import com.ludwig.util.scheduler.BaseSchedulers
import com.ludwig.util.scheduler.BaseSchedulersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by cuongpm on 5/11/19.
 */

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(application: LudwigApplication): Context

    @Binds
    abstract fun bindApplication(application: LudwigApplication): Application

    @Singleton
    @Binds
    abstract fun bindBaseSchedulers(baseSchedulers: BaseSchedulersImpl): BaseSchedulers
}
