package com.ludwig.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by cuongpm on 2019-05-15.
 */

@Entity(tableName = "SentenceEntity")
data class SentenceEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "content")
    @SerializedName("content")
    var content: String = "",

    @ColumnInfo(name = "source")
    @SerializedName("source")
    var source: String = "",

    @ColumnInfo(name = "similar")
    @SerializedName("similar")
    var similar: String = ""
)
