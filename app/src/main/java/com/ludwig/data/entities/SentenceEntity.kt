package com.ludwig.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by cuongpm on 2019-05-15.
 */

data class SentenceEntity constructor(

    @SerializedName("content")
    var content: String = "",

    @SerializedName("source")
    var source: String = "",

    @SerializedName("similar")
    var similar: String = ""
)