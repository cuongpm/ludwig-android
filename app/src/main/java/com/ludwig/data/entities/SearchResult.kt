package com.ludwig.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by cuongpm on 2019-05-15.
 */

data class SearchResult constructor(

    @SerializedName("result_count")
    var resultCount: Int = 0,

    @SerializedName("similar_count")
    var similarCount: Int = 0,

    @SerializedName("related_count")
    var relatedCount: Int = 0,

    @SerializedName("related")
    var related: List<String> = listOf(),

    @SerializedName("result")
    var result: List<SentenceEntity> = listOf()
)