package com.example.tattoprojectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.Adapter.LocalsRecyclerView
import com.example.tattoprojectapp.api.LocalService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Local
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Locals : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocalsRecyclerView
    private var idLocal: String = ""
    private var img: String = ""
    private var name: String = ""
    private var conten: String = ""

    private var locals: List<Local>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.locals)
        recyclerView = findViewById(R.id.rvLocal)
        getLocal()
        //getPosts()

        val botom=findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->replace("home")
                R.id.messages->replace("message")
                R.id.account->replace("profile")
            }
            true
        }


    }

    private fun getLocal() {
        val userService: LocalService = ApiEngine.getApi().create(LocalService::class.java)
        val responseLocal: Call<Local> = userService.getLocals()
        responseLocal.enqueue(object : Callback<Local> {
            override fun onFailure(call: Call<Local>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseLocal.toString())
                Log.e("Error","ADIOS")
            }
            override fun onResponse(call: Call<Local>, response: Response<Local>) {
                Log.e("Error",response.toString())
                Log.e("Info",response.body()!!.locals.toString())
                if(response.body()!!.locals!!.isNotEmpty()) {
                    locals = response.body()!!.locals
                    adapter= LocalsRecyclerView(this@Locals,locals!!)
                    recyclerView.layoutManager= LinearLayoutManager(this@Locals, LinearLayoutManager.VERTICAL,false)
                    recyclerView.adapter=adapter
                }

            }



        })
    }

    private fun replace(string:String){
        var launch: Intent? =null;
        when(string){
            "home"->{
                launch= Intent(this,Locals::class.java)
                startActivity(launch)
            }
            "profile"->{
                launch= Intent(this,Profile::class.java)
                startActivity(launch)
            }
            "message"->{
                launch= Intent(this,Chats::class.java)
                startActivity(launch)
            }
        }
    }
}