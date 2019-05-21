package com.ludwig.data.remote

import com.ludwig.data.entities.SearchResult
import com.ludwig.data.remote.service.LudwigService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private val ludwigService: LudwigService = mock()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSourceImpl(ludwigService)
    }

    @Test
    fun get_sentences() {
        val searchResult = SearchResult(resultCount = 10, similarCount = 5, relatedCount = 2)
        whenever(ludwigService.getSentences()).thenReturn(Single.just(searchResult))
        remoteDataSource.getSentences("")
            .test()
            .assertNoErrors()
            .assertValue { it == searchResult }
    }
}
