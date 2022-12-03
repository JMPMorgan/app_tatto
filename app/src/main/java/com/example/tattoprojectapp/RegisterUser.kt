package com.example.tattoprojectapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.tattoprojectapp.api.PostService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterUser : AppCompatActivity() {
    var user:User?=null
    private val pickImage = 100
    var imgArray:ByteArray? =  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)
        val btnSignin = findViewById<Button>(R.id.btn_signin)
        val btnLoadImage= findViewById<Button>(R.id.btn_loadimage)
        //user!!.email ="Hola"
        btnSignin.setOnClickListener{
            getUserInfo()
        }

        btnLoadImage.setOnClickListener{
            val gallery= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
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
        val encodedString:String =  Base64.getEncoder().encodeToString(this.imgArray)
        val strEncodeImage:String = "data:image/png;base64," + encodedString
        val user= User(null,
                    name.text.toString(),
                    lastname.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    true,
                    birthdate.text.toString(),
                    username.text.toString(),
                    null,
            strEncodeImage
        )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode==pickImage){
            val photo =  data?.data
            val image=findViewById<ImageView>(R.id.img_post)
            image.setImageURI(photo)
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            imgArray =  stream.toByteArray()
        }
    }
}