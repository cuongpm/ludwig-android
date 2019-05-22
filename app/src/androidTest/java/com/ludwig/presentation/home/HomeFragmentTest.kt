package com.ludwig.presentation.home

import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ludwig.R
import com.ludwig.data.entities.SearchResult
import com.ludwig.data.entities.SentenceEntity
import com.ludwig.util.ActivityUtil
import com.ludwig.util.DialogUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.strytelr.com.strytelr.test.util.TestUtil.waitUntil
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.rule.InjectedFragmentTestRule
import test.util.RecyclerViewMatcher.Companion.recyclerViewSizeIs
import test.util.RecyclerViewMatcher.Companion.recyclerViewWithId
import test.util.ViewModelTestUtil
import java.util.concurrent.Callable

class HomeFragmentTest {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dialogUtil: DialogUtil

    private lateinit var activityUtil: ActivityUtil

    private lateinit var homeViewModel: HomeViewModel

    private val loadingState = MutableLiveData<Boolean>()

    private val errorState = MutableLiveData<String>()

    private val dataState = MutableLiveData<SearchResult>()

    private val screen = Screen()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val uiRule = InjectedFragmentTestRule<HomeFragment>({
        it.viewModelFactory = viewModelFactory
        it.dialogUtil = dialogUtil
        it.activityUtil = activityUtil
    }, false)

    @Before
    fun setUp() {
        homeViewModel = mock()
        dialogUtil = mock()
        activityUtil = mock()
        viewModelFactory = ViewModelTestUtil.createFor(homeViewModel)
        whenever(homeViewModel.loadingState).thenReturn(loadingState)
        whenever(homeViewModel.errorState).thenReturn(errorState)
        whenever(homeViewModel.dataState).thenReturn(dataState)
    }

    @Test
    fun press_done_button_to_search_sentences() {
        screen.start()
        screen.verifySearchBoxShown()
        screen.verifyLayoutSearchResult(false)

        val keyword = "keyword"
        screen.typeKeyword(keyword)
        screen.pressSearchIcon()

        waitUntil("Wait for calling getSentences function", Callable {
            verify(homeViewModel).getSentences(keyword)
            true
        }, 100)
    }

    @Test
    fun press_search_icon_to_search_sentences() {
        screen.start()
        screen.verifySearchBoxShown()
        screen.verifyLayoutSearchResult(false)

        val keyword = "keyword"
        screen.typeKeywordAndPressDoneButton(keyword)

        waitUntil("Wait for calling getSentences function", Callable {
            verify(homeViewModel).getSentences(keyword)
            true
        }, 100)
    }

    @Test
    fun verify_loading_state() {
        screen.start()

        homeViewModel.loadingState.postValue(true)
        verify(dialogUtil).showSimpleProgressBar(uiRule.activity)

        homeViewModel.loadingState.postValue(false)
        verify(dialogUtil).closeSimpleProgressBar()
    }

    @Test
    fun verify_error_state() {
        val errorMessage = "error message"
        screen.start()

        homeViewModel.errorState.postValue(errorMessage)
        verify(activityUtil).showToast(uiRule.activity, errorMessage, Toast.LENGTH_SHORT)
    }

    @Test
    fun verify_search_result() {
        screen.start()

        val keyword = "keyword"
        screen.fragment.keyword = keyword
        val sentences = listOf(SentenceEntity(content = "result 1 %s"), SentenceEntity(content = "result 2 %s"))
        val related = listOf("related keyword 1", "related keyword 2")
        val searchResult =
            SearchResult(resultCount = 10, similarCount = 5, relatedCount = 2, related = related, result = sentences)
        homeViewModel.dataState.postValue(searchResult)

        screen.verifyLayoutSearchResult(true)
        screen.verifyResultTitle(keyword)
        screen.verifyResultAmount(searchResult)
        screen.verifyRelatedResult(searchResult.related)
        screen.verifyListResult(sentences)
    }

    inner class Screen {

        val fragment = HomeFragment.newInstance()

        fun start() {
            uiRule.startActivity()
            uiRule.attachFragment(fragment = fragment)
        }

        fun verifySearchBoxShown() {
            onView(withId(R.id.et_search)).check(matches(isDisplayed()))
        }

        fun verifyLayoutSearchResult(shown: Boolean) {
            onView(withId(R.id.layout_result)).check(matches(if (shown) isDisplayed() else not(isDisplayed())))
        }

        fun verifyResultTitle(keyword: String) {
            val title = String.format(uiRule.activity.getString(R.string.result_title), "\"$keyword\"")
            onView(withId(R.id.tv_title)).check(matches(withText(title)))
        }

        fun verifyResultAmount(searchResult: SearchResult) {
            val exactResult = " ${searchResult.result.size}/${searchResult.resultCount} EXACT "
            val similarResult = " ${searchResult.similarCount} SIMILAR "
            val relatedResult = " ${searchResult.relatedCount} RELATED "
            val allResult = "$exactResult  $similarResult  $relatedResult"
            onView(withId(R.id.tv_result_amount)).check(matches(withText(allResult)))
        }

        fun verifyRelatedResult(related: List<String>) {
            onView(withId(R.id.tv_related_result)).check(matches(withText(related.joinToString("  "))))
        }

        fun typeKeyword(keyword: String) {
            onView(withId(R.id.et_search)).perform(typeText(keyword))
        }

        fun typeKeywordAndPressDoneButton(keyword: String) {
            onView(withId(R.id.et_search)).perform(typeText(keyword), pressImeActionButton())
        }

        fun pressSearchIcon() {
            onView(withId(R.id.iv_search)).perform(click())
        }

        fun verifyListResult(sentences: List<SentenceEntity>) {
            onView(withId(R.id.rv_result)).check(matches(recyclerViewSizeIs(sentences.size)))
            sentences.forEachIndexed { position, sentence ->
                onView(recyclerViewWithId(R.id.rv_result).atPositionOnView(position, R.id.tv_sentence))
                    .check(matches(withText(sentence.content)))
            }
        }
    }
}
