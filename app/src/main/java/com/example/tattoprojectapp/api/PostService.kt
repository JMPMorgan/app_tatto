package com.example.tattoprojectapp.api
import com.example.tattoprojectapp.models.User
import com.example.tattoprojectapp.utilis.ApiConstants
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @POST("/api/user")
    fun createNewUser():Call<User>
    @GET("/api/user/{id}")
    fun getUserInfo(@Path("id")id:Int): Call<User>
    @PUT("/api/user/{id}")
    fun updateUser(@Path("id")id:Int):Call<User>
    @DELETE("/api/user/{id}")
    fun deleteUser(@Path("id")id:Int):Call<User>

}