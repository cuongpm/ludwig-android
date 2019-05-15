package com.ludwig.data.remote

import com.ludwig.data.entities.SearchResult
import com.ludwig.data.remote.service.LudwigService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by cuongpm on 5/11/19.
 */

interface RemoteDataSource {
    fun getSentences(keyword: String): Single<SearchResult>
}

class RemoteDataSourceImpl @Inject constructor(
    private val ludwigService: LudwigService
) : RemoteDataSource {

    override fun getSentences(keyword: String): Single<SearchResult> {
        // This is an example api, so we do not send `keyword` parameter to server
        return ludwigService.getSentences()
    }
}