package com.example.tattoprojectapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.Adapter.PostsRecyclerView
import com.example.tattoprojectapp.api.LocalService
import com.example.tattoprojectapp.api.MessageService
import com.example.tattoprojectapp.api.PostsCreationService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Local
import com.example.tattoprojectapp.models.Message
import com.example.tattoprojectapp.models.Post
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPosts : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:PostsRecyclerView
    private var idLocal:String=""
    private var posts:List<Post> ?= null
    private var idUserCreator:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_posts)
        recyclerView=findViewById(R.id.recyclerviewpost)
        idLocal= getIntent().getStringExtra("Local_ID").toString();
        val btnSendMsg= findViewById<Button>(R.id.btn_sendmsg)
        btnSendMsg.setOnClickListener{
            sendMessage()
        }
        Log.e("ID LOCAL EN ALL POSTS",idLocal)

        if(idLocal.isNotEmpty()){
            Log.e("ENTRO ID LOCAL",idLocal)
            getLocal()
            getPosts()

        }
        else{
            Log.e("Entro","Aqui2")

            getLocalPerUser()
        }
        //getPosts()
        Log.e("POST",posts.toString())



    }

    private fun getLocalPerUser(){
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

    private fun getLocal(){
        val service: LocalService=ApiEngine.getApi().create(LocalService::class.java)
        val response:Call<Local> =  service.getLocal(idLocal)
        response.enqueue(object :Callback<Local>{
            override fun onResponse(call: Call<Local>, response: Response<Local>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
                val localTitle= findViewById<TextView>(R.id.title_local)
                val localImg= findViewById<ImageView>(R.id.imageLocal)
                val localDescription=findViewById<TextView>(R.id.description_post)
                localDescription.text="Schedule: "+response.body()!!.schedule+ " Weekdays: "+response.body()!!.weekdays
                localTitle.text=response.body()!!.name.toString()
                Picasso.get().load(response.body()!!.img.toString()).into(localImg)
                idUserCreator=response.body()!!.userCreator.toString()
            }

            override fun onFailure(call: Call<Local>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")
            }

        })
    }

    private fun getPosts(){
        val service: PostsCreationService = ApiEngine.getApi().create(PostsCreationService::class.java)
        Log.e("ID LOCAL",idLocal)
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



    private fun  sendMessage(){
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val service: MessageService=ApiEngine.getApi().create(MessageService::class.java)
        val message=Message(null,null,null,"Este usuario quiere informacion",id,idUserCreator,null)
        val response:Call<String> =service.sendMessage(message)
        response.enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")
            }

        })
    }
}