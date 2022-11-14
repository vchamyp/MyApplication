package com.example.myapplication

import com.google.gson.annotations.SerializedName


data class Model (

    @SerializedName("type" ) var type : String?         = null,
    @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)