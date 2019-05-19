package com.ludwig.presentation.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ludwig.R
import com.ludwig.presentation.base.BaseFragment
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

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var homeAdapter: HomeAdapter

    private var keyword: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        initUI()
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

    private fun handleDataState() {
        homeViewModel.dataState.observe(this, Observer { searchResult ->
            // Since I haven't had real api, so I will do a little trick here.
            // Add keyword into the result and then we can highlight it
            val result = searchResult.result.apply { forEach { it.content = String.format(it.content, keyword) } }

            layout_result.visibility = View.VISIBLE
            tv_title.text = String.format(getString(R.string.result_title), keyword)
            homeAdapter.setData(result, keyword)
        })
    }
}
