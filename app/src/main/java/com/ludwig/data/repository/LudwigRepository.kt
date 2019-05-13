package com.ludwig.data.repository

import com.ludwig.data.remote.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by cuongpm on 5/11/19.
 */

interface LudwigRepository {
    fun getSentences(keyword: String): Single<String>
}

class LudwigRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : LudwigRepository {

    override fun getSentences(keyword: String): Single<String> {
        return remoteDataSource.getSentences(keyword)
    }
}
