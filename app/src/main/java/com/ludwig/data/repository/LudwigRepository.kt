package com.ludwig.data.repository

import com.ludwig.data.entities.SearchResult
import com.ludwig.data.local.LocalDataSource
import com.ludwig.data.remote.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by cuongpm on 5/11/19.
 */

interface LudwigRepository {
    fun getSentences(keyword: String): Single<SearchResult>
}

class LudwigRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : LudwigRepository {

    override fun getSentences(keyword: String): Single<SearchResult> {
        return remoteDataSource.getSentences(keyword)
            .doOnSuccess { searchResult ->
                searchResult.result.map { localDataSource.saveSentence(it) }
            }
    }
}
