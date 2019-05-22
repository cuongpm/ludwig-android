package com.ludwig.di.module

import androidx.room.Room
import com.ludwig.LudwigApplication
import com.ludwig.data.local.room.AppDatabase
import com.ludwig.data.local.room.SentenceDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cuongpm on 5/11/19.
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: LudwigApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "ludwig.db").build()
    }

    @Singleton
    @Provides
    fun provideSentenceDao(database: AppDatabase): SentenceDao = database.sentenceDao()
}
