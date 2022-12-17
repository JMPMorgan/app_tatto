package com.example.tattoprojectapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
    private var idReceiver:String=""
    private var messages:ArrayList<Message>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messages)
        recyclerView=findViewById(R.id.messageRV)
        idConversation= getIntent().getStringExtra("Conversation_ID").toString();
        idReceiver=getIntent().getStringExtra("Receiver_ID").toString()
        getMessages()
        val btnSendMessage=findViewById<Button>(R.id.btn_sendmsg)
        btnSendMessage.setOnClickListener{
            sendMessage()
        }
    }

    private  fun getMessages(){
        val messageService:MessageService=ApiEngine.getApi().create(MessageService::class.java)
        val responseMessage: Call <ArrayList<Message>> =messageService.getMessages(idConversation)
        responseMessage.enqueue(object :Callback<ArrayList<Message>>{
            override fun onResponse(call: Call<ArrayList<Message>>, response: Response<ArrayList<Message>>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
                var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
                val id=sp.getString("iduser","")
                messages=response.body()
                adapter= MessagesRecyclerView(messages!!,id!!)
                recyclerView.layoutManager=LinearLayoutManager(this@Messages,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter=adapter

            }

            override fun onFailure(call: Call<ArrayList<Message>>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseMessage.toString())
                Log.e("Error","ADIOS")
            }

        })
    }

    private fun sendMessage(){
        val messageContainer= findViewById<EditText>(R.id.input_description_post)
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val message= Message(null,null,null,messageContainer.text.toString(),id.toString(),idReceiver,idConversation)
        val messageService:MessageService=ApiEngine.getApi().create(MessageService::class.java)
        val responseMessage:Call <String> =messageService.sendMessage(message)
        responseMessage.enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("RESPONSE",response.body().toString())
                messageContainer.text.clear()
                getMessages()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseMessage.toString())
                Log.e("Error","ADIOS")
            }

        })

    }
}