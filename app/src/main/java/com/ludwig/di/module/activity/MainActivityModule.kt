package com.ludwig.di.module.activity

import com.ludwig.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

}
