package com.example.tattoprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tattoprojectapp.api.PostService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUser : AppCompatActivity() {
    var user:User?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)
        val btnSignin = findViewById<Button>(R.id.btn_signin)
        //user!!.email ="Hola"
        btnSignin.setOnClickListener{
            getUserInfo()
        }
    }


    private fun getUserInfo(){
        val name= findViewById<EditText>(R.id.input_name)
        val lastname = findViewById<EditText>(R.id.input_lastname)
        val username= findViewById<EditText>(R.id.input_username)
        val birthdate= findViewById<EditText>(R.id.input_birthdate)
        val email = findViewById<EditText>(R.id.input_email)
        val password=findViewById<EditText>(R.id.input_password)
        val cpassword=findViewById<EditText>(R.id.input_cpassword)
        val user= User(null,
                    name.text.toString(),
                    lastname.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    true,
                    birthdate.text.toString(),
                    username.text.toString(),
                    null)
        val service:PostService= ApiEngine.getApi().create(PostService::class.java)
        Log.d("USER",user.toString())
        val response: Call<User> = service.createNewUser(user)
        response.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("ERROR",t.toString())
                Log.d("ERROR",response.toString())
                Toast.makeText(this@RegisterUser,"Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val data = response.body()
                val data2 = response.message()
                Log.d("ERROR",response.errorBody().toString())
                Log.d("ERROR",response.toString())
                Log.d("ERROR",data.toString())
                Log.d("ERROR",data2.toString())

                Toast.makeText(this@RegisterUser,"Hola",Toast.LENGTH_LONG).show()
            }
        })
    }
}