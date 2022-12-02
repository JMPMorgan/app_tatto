package com.example.tattoprojectapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.Adapter.PostsRecyclerView
import com.example.tattoprojectapp.api.LocalService
import com.example.tattoprojectapp.api.PostsCreationService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Local
import com.example.tattoprojectapp.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPosts : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:PostsRecyclerView
    private var idLocal:String=""
    private var posts:List<Post> ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_posts)
        recyclerView=findViewById(R.id.recyclerviewpost)
        getLocal()
        //getPosts()
        Log.e("POST",posts.toString())




    }

    private fun getLocal(){
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val userService: LocalService= ApiEngine.getApi().create(LocalService::class.java)
        val responseLocal:Call<Local> = userService.getLocalPerUser(id!!.toString())
        responseLocal.enqueue(object :Callback<Local>{
            override fun onFailure(call: Call<Local>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseLocal.toString())
                Log.e("Error","ADIOS")
            }
            override fun onResponse(call: Call<Local>, response: Response<Local>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body()!!.local!!.id.toString())
                idLocal=response.body()!!.local!!.id.toString()
                getPosts()
            }
        })
    }

    private fun getPosts(){
        val service: PostsCreationService = ApiEngine.getApi().create(PostsCreationService::class.java)

        val response: Call<Post> = service.getPostsPerLocal(idLocal)
        response.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")
            }
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
                posts=response.body()!!.posts
                adapter= PostsRecyclerView(posts!!)
                recyclerView.layoutManager=LinearLayoutManager(this@AllPosts,LinearLayoutManager.HORIZONTAL,false)
                recyclerView.adapter=adapter
            }
        })
    }
}