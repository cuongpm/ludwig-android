package com.ludwig.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ludwig.data.entities.SearchResult
import com.ludwig.data.repository.LudwigRepository
import com.ludwig.util.scheduler.BaseSchedulers
import com.ludwig.util.scheduler.StubbedSchedulers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val ludwigRepository: LudwigRepository = mock()

    private val baseSchedulers: BaseSchedulers = StubbedSchedulers()

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(ludwigRepository, baseSchedulers)
    }

    @Test
    fun `search sentences by keyword successfully`() {
        val keyword = "keyword"
        val searchResult = SearchResult(resultCount = 10, similarCount = 5, relatedCount = 2)
        whenever(ludwigRepository.getSentences(keyword)).thenReturn(Single.just(searchResult))
        homeViewModel.getSentences(keyword)

        assertEquals(searchResult, homeViewModel.dataState.value)
    }

    @Test
    fun `search sentences by keyword failed`() {
        val keyword = "keyword"
        val errorMessage = "error message"
        whenever(ludwigRepository.getSentences(keyword)).thenAnswer { Single.error<Exception>(Throwable(errorMessage)) }
        homeViewModel.getSentences(keyword)

        assertEquals(errorMessage, homeViewModel.errorState.value)
    }
}
