package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class User(
    //@SerializedName esto funciona si la api manda un valor con un nombre distitnto
    //Como user_id en lugar de userid la class pueda hacer match con la propiedad
    @SerializedName("_id")
    var  userid:Int,
    @SerializedName("name")
    var name:String,
    @SerializedName("lastname")
    var lastname:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String,
    @SerializedName("status")
    var status:Boolean,
    @SerializedName("username")
    var username:String,
    @SerializedName("token")
    var jwt:String
)
