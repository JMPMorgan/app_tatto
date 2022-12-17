package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageService {
    @GET("/api/message/{id}")
    fun getMessages(@Path("id")id:String): Call<ArrayList<Message>>
    @POST("/api/message/")
    fun sendMessage(@Body message:Message): Call<String>
}