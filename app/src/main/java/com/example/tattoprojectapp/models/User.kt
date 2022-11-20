package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class User(
    //@SerializedName esto funciona si la api manda un valor con un nombre distitnto
    //Como user_id en lugar de userid la class pueda hacer match con la propiedad
    @SerializedName("_id")
    var  userid:String?=null,
    @SerializedName("name")
    var name:String?=null,
    @SerializedName("lastname")
    var lastname:String?=null,
    @SerializedName("email")
    var email:String?=null,
    @SerializedName("password")
    var password:String?=null,
    @SerializedName("status")
    var status:Boolean?=null,
    @SerializedName("birthday")
    var birthday:String?=null,
    @SerializedName("username")
    var username:String?=null,
    @SerializedName("token")
    var jwt:String?=null
)
