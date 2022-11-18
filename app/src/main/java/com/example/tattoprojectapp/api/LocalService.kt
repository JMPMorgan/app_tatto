package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Local
import retrofit2.Call
import retrofit2.http.*

interface LocalService {
    @GET("/api/local")
    fun getLocals(): Call<List<Local>>
    @GET("/api/local/{id}")
    fun getLocal(@Path("id")id : Int):Call<Local>
    @POST("/api/local")
    fun createLocal():Call<Int>

}