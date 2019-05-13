package com.ludwig.di.module

import android.app.Application
import android.content.Context
import com.ludwig.LudwigApplication
import dagger.Binds
import dagger.Module

/**
 * Created by cuongpm on 5/11/19.
 */

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(application: LudwigApplication): Context

    @Binds
    abstract fun bindApplication(application: LudwigApplication): Application
}
