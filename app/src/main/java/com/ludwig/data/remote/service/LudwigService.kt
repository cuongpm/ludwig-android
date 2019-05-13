package com.ludwig.data.remote.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LudwigService {

    @GET("s/{keyword}")
    fun getSentences(@Path("keyword") keyword: String): Single<String>
}
