package com.example.tattoprojectapp.api

import retrofit2.http.*

interface LocalService {
    @GET("/api/local")
    fun getLocals():Call
    @GET("/api/local/{id}")
    fun getLocal(@Path("id")id : Int):Call
    @POST("/api/local")
    fun createLocal():Call

}