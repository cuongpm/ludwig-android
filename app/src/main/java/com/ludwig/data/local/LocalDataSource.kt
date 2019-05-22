package com.ludwig.data.local

import com.ludwig.data.entities.SentenceEntity
import com.ludwig.data.local.room.SentenceDao
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by cuongpm on 5/11/19.
 */

interface LocalDataSource {
    fun getSentences(): Single<List<SentenceEntity>>
    fun saveSentence(sentenceEntity: SentenceEntity)
}

class LocalDataSourceImpl @Inject constructor(
    private val sentenceDao: SentenceDao
) : LocalDataSource {
    override fun getSentences(): Single<List<SentenceEntity>> {
        return sentenceDao.getSentences()
    }

    override fun saveSentence(sentenceEntity: SentenceEntity) {
        sentenceDao.insertSentence(sentenceEntity)
    }
}
