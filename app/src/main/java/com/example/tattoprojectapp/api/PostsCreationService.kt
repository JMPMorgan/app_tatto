package com.example.tattoprojectapp.api

import retrofit2.http.*

interface PostsCreationService {
    @GET("/api/posts")
    fun getAllPosts()
    @GET("/api/posts/{id}")
    fun getPost(@Path("id")id:Int)
    @POST("/api/posts")
    fun createPost()
}