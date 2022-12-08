package com.example.tattoprojectapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.tattoprojectapp.api.AuthService
import com.example.tattoprojectapp.api.PostService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        val btnEditProfile=findViewById<Button>(R.id.btn_editprofile2)
        val btnCreateLocal= findViewById<Button>(R.id.btn_createlocal)
        val btnLocales=findViewById<Button>(R.id.btnlocal)
        val btnTmpPost= findViewById<Button>(R.id.btn_temp)
        val btnTmpAllPost= findViewById<Button>(R.id.btn_temp2)
        val btnMessages=findViewById<Button>(R.id.btn_message)
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val service: PostService = ApiEngine.getApi().create(PostService::class.java)
        val response: Call<User> = service.getUserInfo(id!!.toString())
        response.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")

                Toast.makeText(this@Profile,"Error", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
               val user = response.body()
                val image=findViewById<ImageView>(R.id.img_logo)
                Log.e("Error","HOLA")
                Log.e("Error",user.toString())
                Picasso.get()
                    .load(user!!.file.toString())
                    .into(image)
            }


        })

        btnEditProfile.setOnClickListener{
            val laucnh= Intent(this,EditProfile::class.java)
            startActivity(laucnh)
        }

        btnLocales.setOnClickListener {
            val laucnh= Intent(this,Locals::class.java)
            startActivity(laucnh)
        }


        btnCreateLocal.setOnClickListener {
            val launch = Intent(this, SignInLocal::class.java)
            startActivity(launch)
        }

        btnTmpPost.setOnClickListener{
            val launch=Intent(this,NewPost::class.java)
            startActivity(launch)
        }
        btnTmpAllPost.setOnClickListener{
            val launch=Intent(this,AllPosts::class.java)
            startActivity(launch)
        }

        btnMessages.setOnClickListener{
            val launch=Intent(this,Chats::class.java)
            startActivity(launch)
        }



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