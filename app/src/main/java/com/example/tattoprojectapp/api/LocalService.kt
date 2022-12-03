package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Local
import retrofit2.Call
import retrofit2.http.*

interface LocalService {
    @GET("/api/local")
    fun getLocals(): Call<Local>
    @GET("/api/local/{id}")
    fun getLocal(@Path("id")id : String):Call<Local>
    @POST("/api/local/")
    fun createLocal(@Body local:Local):Call<Local>
    @GET("/api/local/user/{id}")
    fun getLocalPerUser(@Path("id")id:String):Call<Local>

}