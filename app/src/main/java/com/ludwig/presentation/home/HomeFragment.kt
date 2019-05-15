package com.ludwig.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ludwig.R
import com.ludwig.presentation.base.BaseFragment
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getSentences("Deal+with+it")
        handleDataState()
    }

    private fun handleDataState() {
        homeViewModel.dataState.observe(this, Observer { searchResult ->
            tv_content.text = searchResult.result.joinToString("\n\n")
        })
    }
}
