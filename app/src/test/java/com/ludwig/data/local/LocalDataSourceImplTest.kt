package com.ludwig.data.local

import com.ludwig.data.entities.SentenceEntity
import com.ludwig.data.local.room.SentenceDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class LocalDataSourceImplTest {

    private val sentenceDao: SentenceDao = mock()

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        localDataSource = LocalDataSourceImpl(sentenceDao)
    }

    @Test
    fun get_sentences() {
        val listSentences = listOf(
            SentenceEntity(content = "sentence 1"),
            SentenceEntity(content = "sentence 2")
        )
        whenever(sentenceDao.getSentences()).thenReturn(Single.just(listSentences))
        localDataSource.getSentences()
            .test()
            .assertNoErrors()
            .assertValue { it == listSentences }
    }

    @Test
    fun save_sentence() {
        val sentence = SentenceEntity(content = "sentence")
        localDataSource.saveSentence(sentence)
        verify(sentenceDao).insertSentence(sentence)
    }
}
