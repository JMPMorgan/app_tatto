package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/login")
    fun logIn():Call<User>
}