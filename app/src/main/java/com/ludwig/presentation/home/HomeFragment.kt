package com.ludwig.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ludwig.R
import com.ludwig.data.entities.SearchResult
import com.ludwig.presentation.base.BaseFragment
import com.ludwig.util.ActivityUtil
import com.ludwig.util.DialogUtil
import com.ludwig.util.StringUtil
import com.ludwig.widget.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


/**
 * Created by cuongpm on 5/11/19.
 */

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dialogUtil: DialogUtil

    @Inject
    lateinit var activityUtil: ActivityUtil

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var homeAdapter: HomeAdapter

    @VisibleForTesting
    internal var keyword: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        initUI()
        handleLoadingState()
        handleErrorState()
        handleDataState()
    }

    private fun initUI() {
        homeAdapter = HomeAdapter(requireContext())
        rv_result.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
            addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelOffset(R.dimen.padding_small)))
        }

        iv_search.setOnClickListener {
            keyword = et_search.text.toString().trim()
            if (keyword.isNotEmpty()) {
                homeViewModel.getSentences(keyword)
            }
        }

        et_search.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    keyword = et_search.text.toString().trim()
                    if (keyword.isNotEmpty()) {
                        homeViewModel.getSentences(keyword)
                    }
                    return true
                }
                return false
            }
        })
    }

    private fun handleLoadingState() {
        homeViewModel.loadingState.observe(this, Observer { isShowLoading ->
            activity?.let {
                if (isShowLoading) {
                    dialogUtil.showSimpleProgressBar(it)
                } else {
                    dialogUtil.closeSimpleProgressBar()
                }
            }
        })
    }

    private fun handleErrorState() {
        homeViewModel.errorState.observe(this, Observer { message ->
            activity?.let {
                activityUtil.showToast(it, message, Toast.LENGTH_SHORT)
            }
        })
    }

    private fun handleDataState() {
        homeViewModel.dataState.observe(this, Observer { searchResult ->
            // Since I haven't had real api, so I will do a little trick here.
            // Add keyword into the result and then we can highlight it
            val result = searchResult.result.apply {
                forEach {
                    it.content = String.format(it.content, if (it.similar.isEmpty()) keyword else it.similar)
                }
            }

            activity?.let {
                it.runOnUiThread {
                    buildResultLayout(it, searchResult)
                    homeAdapter.setData(result, keyword)
                }
            }
        })
    }

    private fun buildResultLayout(context: Context, searchResult: SearchResult) {
        val exactResult = " ${searchResult.result.size}/${searchResult.resultCount} EXACT "
        val similarResult = " ${searchResult.similarCount} SIMILAR "
        val relatedResult = " ${searchResult.relatedCount} RELATED "
        val allResult = "$exactResult  $similarResult  $relatedResult"
        val listKeywords = listOf(exactResult, similarResult, relatedResult)
        val listColorIds = listOf(R.color.color_blue, R.color.color_yellow, R.color.color_pink)
        tv_result_amount.text = StringUtil.highlightMultipleKeywords(
            context,
            allResult,
            listKeywords,
            listColorIds,
            isBold = false,
            isUnderLine = true
        )
        tv_title.text = StringUtil.highlightKeyword(
            context,
            String.format(getString(R.string.result_title), "\"$keyword\""),
            "\"$keyword\"",
            0,
            isBold = true,
            isUnderLine = false
        )
        tv_related_result.text = StringUtil.highlightMultipleKeywords(
            context,
            searchResult.related.joinToString("  "),
            searchResult.related,
            listOf(R.color.color_pink),
            isBold = false,
            isUnderLine = true
        )
        layout_result.visibility = View.VISIBLE
    }
}
