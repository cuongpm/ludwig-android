package com.ludwig.presentation.home

import androidx.lifecycle.MutableLiveData
import com.ludwig.data.entities.SearchResult
import com.ludwig.data.repository.LudwigRepository
import com.ludwig.presentation.base.BaseViewModel
import com.ludwig.util.scheduler.BaseSchedulers
import javax.inject.Inject


/**
 * Created by cuongpm on 5/11/19.
 */

class HomeViewModel @Inject constructor(
    private val ludwigRepository: LudwigRepository,
    private val baseSchedulers: BaseSchedulers
) : BaseViewModel() {

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String>()
    val dataState = MutableLiveData<SearchResult>()

    fun getSentences(keyword: String) {
        ludwigRepository.getSentences(keyword)
            .subscribeOn(baseSchedulers.io)
            .observeOn(baseSchedulers.mainThread)
            .doOnSubscribe { loadingState.postValue(true) }
            .doAfterTerminate { loadingState.postValue(false) }
            .subscribe({ searchResult ->
                dataState.postValue(searchResult)
            }, { error ->
                error.printStackTrace()
                errorState.postValue(error.message)
            }).let { compositeDisposable.add(it) }
    }
}
