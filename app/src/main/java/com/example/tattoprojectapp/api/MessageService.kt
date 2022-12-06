package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Message
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MessageService {
    @GET("/api/message/{id}")
    fun getMessages(@Path("id")id:String): Call<List<Message>>
}