package com.example.tattoprojectapp.models

import com.google.gson.annotations.SerializedName

data class User(
    //@SerializedName esto funciona si la api manda un valor con un nombre distitnto
    //Como user_id en lugar de userid la class pueda hacer match con la propiedad
    @SerializedName("userId")
    val  userid:Int,
)
