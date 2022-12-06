package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class Conversations(
    @SerializedName("_id")
    var id:String?=null,
    @SerializedName("user")
    var user:User?=null,
    @SerializedName("artist")
    var artists:User?=null,
    @SerializedName("idreceiver")
    var idreceiver:String?=null,
    @SerializedName("message")
    var message:String?=null,
    @SerializedName("status")
    var status:Boolean?=null
)
