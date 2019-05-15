package com.ludwig.data.remote.service

import com.ludwig.data.entities.SearchResult
import io.reactivex.Single
import retrofit2.http.GET

interface LudwigService {
    
    @GET("ludwig.json")
    fun getSentences(): Single<SearchResult>
}
