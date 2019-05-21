package com.ludwig.di.module

import android.app.Application
import android.content.Context
import com.ludwig.LudwigApplication
import com.ludwig.util.DialogUtil
import com.ludwig.util.DialogUtilImpl
import com.ludwig.util.IntentUtil
import com.ludwig.util.IntentUtilImpl
import com.ludwig.util.fragment.FragmentFactory
import com.ludwig.util.fragment.FragmentFactoryImpl
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

    @Singleton
    @Binds
    abstract fun bindFragmentFactory(fragmentFactory: FragmentFactoryImpl): FragmentFactory

    @Singleton
    @Binds
    abstract fun bindIntentUtil(intentUtil: IntentUtilImpl): IntentUtil

    @Singleton
    @Binds
    abstract fun bindDialogUtil(dialogUtil: DialogUtilImpl): DialogUtil
}
