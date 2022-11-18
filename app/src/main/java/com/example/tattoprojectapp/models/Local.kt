package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class Local(
    @SerializedName("_id")
    var id:Int,
    @SerializedName("user")
    var userCreator:Int,
    @SerializedName("status")
    var status:Boolean,
    @SerializedName("location")
    var location:String
)
