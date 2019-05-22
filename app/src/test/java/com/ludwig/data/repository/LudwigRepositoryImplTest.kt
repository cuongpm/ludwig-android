package com.ludwig.data.repository

import com.ludwig.data.entities.SearchResult
import com.ludwig.data.entities.SentenceEntity
import com.ludwig.data.local.LocalDataSource
import com.ludwig.data.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class LudwigRepositoryImplTest {

    private val remoteDataSource: RemoteDataSource = mock()

    private val localDataSource: LocalDataSource = mock()

    private lateinit var ludwigRepository: LudwigRepository

    @Before
    fun setUp() {
        ludwigRepository = LudwigRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun get_sentences() {
        val keyword = "keyword"
        val listSentences = listOf(
            SentenceEntity(content = "sentence 1"),
            SentenceEntity(content = "sentence 2")
        )
        val searchResult = SearchResult(resultCount = 10, similarCount = 5, relatedCount = 2, result = listSentences)
        whenever(remoteDataSource.getSentences(keyword)).thenReturn(Single.just(searchResult))

        ludwigRepository.getSentences(keyword)
            .test()
            .assertNoErrors()
            .assertValue { it == searchResult }

        listSentences.map {
            verify(localDataSource).saveSentence(it)
        }
    }
}
