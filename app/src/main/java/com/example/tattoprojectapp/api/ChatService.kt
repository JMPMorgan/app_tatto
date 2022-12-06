package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Conversations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {
    @GET("/api/conversations/{id}")
    fun getConversations(@Path("id")id:String):Call <List<Conversations>>
}