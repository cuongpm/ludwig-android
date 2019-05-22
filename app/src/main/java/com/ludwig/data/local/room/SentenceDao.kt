package com.ludwig.data.local.room

import androidx.room.*
import com.ludwig.data.entities.SentenceEntity
import io.reactivex.Single

@Dao
interface SentenceDao {

    @Query("SELECT * FROM SentenceEntity")
    fun getSentences(): Single<List<SentenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSentence(sentenceEntity: SentenceEntity)

    @Delete
    fun deleteSentence(sentenceEntity: SentenceEntity)
}
