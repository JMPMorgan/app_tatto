package com.example.tattoprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.tattoprojectapp.models.User

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
        Toast.makeText(this,"Hola", Toast.LENGTH_SHORT).show()
    }
}