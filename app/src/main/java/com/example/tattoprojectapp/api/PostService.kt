package com.example.tattoprojectapp.api
import com.example.tattoprojectapp.models.User
import com.example.tattoprojectapp.utilis.ApiConstants
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("/api/")
    fun getUserInfo(): Call<List<User>>


}