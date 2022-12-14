package com.example.tattoprojectapp.api
import com.example.tattoprojectapp.models.User
import com.example.tattoprojectapp.utilis.ApiConstants
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @Headers("Content-Type: application/json")
    @POST("/api/user/")
    fun createNewUser(@Body user:User):Call<User>
    @GET("/api/user/{id}")
    fun getUserInfo(@Path("id")id:String): Call<User>
    @PUT("/api/user/{id}")
    fun updateUser(@Path("id")id:String,@Body user:User):Call<User>
    @DELETE("/api/user/{id}")
    fun deleteUser(@Path("id")id:String):Call<User>
    @GET("/api/user/")
    fun getUsers():Call<List<User>>
}