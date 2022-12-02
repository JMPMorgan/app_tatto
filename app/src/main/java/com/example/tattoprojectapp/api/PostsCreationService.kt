package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Post
import retrofit2.Call
import retrofit2.http.*

interface PostsCreationService {
    @GET("/api/posts")
    fun getAllPosts():Call<List<Post>>
    @GET("/api/posts/{id}")
    fun getPost(@Path("id")id:String):Call<Post>
    @POST("/api/posts")
    fun createPost(@Body post:Post):Call<Post>
    @GET("/api/local/post/{id}")
    fun getPostsPerLocal(@Path("id") id: String):Call<Post>
}