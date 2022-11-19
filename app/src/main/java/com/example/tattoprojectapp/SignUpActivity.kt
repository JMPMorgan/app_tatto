package com.example.tattoprojectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val btnLogin= findViewById<Button>(R.id.btn_login)
        val btnSignUp = findViewById<Button>(R.id.btn_signin)
        btnLogin.setOnClickListener{
            val launch = Intent(this,TattoCenterList::class.java)
            startActivity(launch)
        }
        btnSignUp.setOnClickListener{
            val launch = Intent(this,RegisterUser::class.java)
            startActivity(launch)
        }
    }
}