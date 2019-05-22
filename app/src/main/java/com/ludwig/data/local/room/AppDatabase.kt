package com.ludwig.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ludwig.data.entities.SentenceEntity

@Database(entities = [SentenceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sentenceDao(): SentenceDao
}
