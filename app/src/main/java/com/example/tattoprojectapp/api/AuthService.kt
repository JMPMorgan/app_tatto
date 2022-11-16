package com.example.tattoprojectapp.api

import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/login")
    fun logIn()
}