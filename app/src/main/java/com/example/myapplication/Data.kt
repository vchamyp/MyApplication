package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("img") var img: String? = null,
    @SerializedName("img_cnt") var img_cnt: String? = null
)
