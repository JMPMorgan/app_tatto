package com.example.tattoprojectapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.Adapter.ChatsRecyclerView
import com.example.tattoprojectapp.Adapter.MessagesRecyclerView
import com.example.tattoprojectapp.api.MessageService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Messages : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:MessagesRecyclerView
    private var idConversation:String=""
    private var messages:List<Message>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messages)
        recyclerView=findViewById(R.id.messageRV)
        idConversation= getIntent().getStringExtra("Conversation_ID").toString();
        getMessages()
    }

    private  fun getMessages(){
        val messageService:MessageService=ApiEngine.getApi().create(MessageService::class.java)
        val responseMessage: Call <List<Message>> =messageService.getMessages(idConversation)
        responseMessage.enqueue(object :Callback<List<Message>>{
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
                var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
                val id=sp.getString("iduser","")
                messages=response.body()
                adapter= MessagesRecyclerView(messages!!,id!!)
                recyclerView.layoutManager=LinearLayoutManager(this@Messages,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter=adapter

            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseMessage.toString())
                Log.e("Error","ADIOS")
            }

        })
    }
}