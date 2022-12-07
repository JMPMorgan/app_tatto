package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class  Message(
    @SerializedName("_id")
    var id:String?=null,
    @SerializedName("sender")
    var sender:User?=null,
    @SerializedName("receiver")
    var receiver:User?=null,
    @SerializedName("message")
    var message:String?=null,
    var idsender:String?=null,
    var idreceiver:String?=null,
    var conversation:String?=null
)
