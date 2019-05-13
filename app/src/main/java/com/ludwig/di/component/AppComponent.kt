package com.ludwig.di.component

import com.ludwig.LudwigApplication
import com.ludwig.di.module.ActivityBindingModule
import com.ludwig.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by cuongpm on 5/11/19.
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<LudwigApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: LudwigApplication): Builder

        fun build(): AppComponent
    }
}
