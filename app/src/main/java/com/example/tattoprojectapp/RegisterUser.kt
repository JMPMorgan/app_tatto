package com.example.tattoprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)

        val btnSignin = findViewById<Button>(R.id.btn_signin)
        btnSignin.setOnClickListener{

        }
    }
}