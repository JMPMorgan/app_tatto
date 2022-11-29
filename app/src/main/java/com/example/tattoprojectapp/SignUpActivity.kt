package com.example.tattoprojectapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tattoprojectapp.api.AuthService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val btnLogin= findViewById<Button>(R.id.btn_login)
        val btnSignUp = findViewById<Button>(R.id.btn_signin)
        btnLogin.setOnClickListener{
            val isLoggin=signIn()
        }
        btnSignUp.setOnClickListener{
            val launch = Intent(this,RegisterUser::class.java)
            startActivity(launch)
        }
    }

    private fun signIn(){
        val email=findViewById<EditText>(R.id.input_email)
        val password= findViewById<EditText>(R.id.input_password)
        val user = User("","","", email.text.toString(),password.text.toString(),null,null,null,null)
        val service:AuthService = ApiEngine.getApi().create(AuthService::class.java)
        val response: Call<User> = service.logIn(user)
        response.enqueue(object :Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("ERROR",t.toString())
                Log.d("ERROR",response.toString())
                Toast.makeText(this@SignUpActivity,"Error", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                var sharedPreferences:SharedPreferences?=null;
                sharedPreferences= getSharedPreferences("userData",Context.MODE_PRIVATE)
                val user = response.body()
                if(user!!.jwt!==null || user!!.userid !==null){
                    val jwt=user!!.jwt
                    val id = user!!.userid
                    val editor=sharedPreferences.edit()
                    editor.putString("jwt",jwt)
                    editor.putString("iduser",id)
                    editor.commit()
                    //Toast.makeText(this@SignUpActivity,id,Toast.LENGTH_SHORT).show()
                    var sp:SharedPreferences=applicationContext.getSharedPreferences("userData",Context.MODE_PRIVATE)
                    val jwtsp=sp.getString("jwt","")
                    //Toast.makeText(this@SignUpActivity,jwt, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@SignUpActivity,jwtsp, Toast.LENGTH_SHORT).show()
                    val launch = Intent(this@SignUpActivity,AllPosts::class.java)
                    startActivity(launch)
                }
                Toast.makeText(this@SignUpActivity,"Error no se pudo encontrar el usuario",Toast.LENGTH_SHORT).show()

            }
        })
    }
}