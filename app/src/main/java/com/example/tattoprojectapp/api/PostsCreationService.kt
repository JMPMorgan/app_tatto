package com.example.tattoprojectapp.api

import com.example.tattoprojectapp.models.Post
import retrofit2.Call
import retrofit2.http.*

interface PostsCreationService {
    @GET("/api/posts")
    fun getAllPosts():Call<List<Post>>
    @GET("/api/posts/{id}")
    fun getPost(@Path("id")id:Int):Call<Post>
    @POST("/api/posts")
    fun createPost():Call<Post>
}