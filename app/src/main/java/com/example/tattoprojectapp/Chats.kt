package com.example.tattoprojectapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.Adapter.ChatsRecyclerView
import com.example.tattoprojectapp.api.ChatService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Conversations
import com.example.tattoprojectapp.models.Local
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Chats : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:ChatsRecyclerView
    private var chats:List<Conversations>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chats)
        recyclerView=findViewById(R.id.rvChat)
        getChats()
    }


    private fun getChats(){
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val chatsService:ChatService=ApiEngine.getApi().create(ChatService::class.java)
        val responseChats:Call <List<Conversations>> =chatsService.getConversations(id!!.toString())
        responseChats.enqueue(object :Callback <List<Conversations>>{
            override fun onFailure(call: Call<List<Conversations>>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseChats.toString())
                Log.e("Error","ADIOS")
            }
            override fun onResponse(call: Call<List<Conversations>>, response: Response<List<Conversations>>) {
                chats= response.body()
                adapter= ChatsRecyclerView(this@Chats,chats!!)
                recyclerView.layoutManager=LinearLayoutManager(this@Chats,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter=adapter
            }
        })
    }
}