package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("_id")
    var id:Int,
    @SerializedName("user")
    var userid:Int,
    @SerializedName("description")
    var description:String,
    @SerializedName("local")
    var localid:Int,
    @SerializedName("img")
    var img:String?=null
)
