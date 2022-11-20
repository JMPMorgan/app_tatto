package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    fun logIn(@Body user:User):Call<User>
}