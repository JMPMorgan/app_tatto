package com.example.tattoprojectapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.tattoprojectapp.api.AuthService
import com.example.tattoprojectapp.api.PostService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfile : AppCompatActivity() {
    private val pickImage = 100
    var imgArray:ByteArray? =  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        val buttonUploadImage=findViewById<Button>(R.id.btn_loadimage)
        val buttonEditProfile= findViewById<Button>(R.id.btn_edituser)

        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val service: PostService = ApiEngine.getApi().create(PostService::class.java)
        val response: Call<User> = service.getUserInfo(id!!.toString())
        response.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")
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
        buttonUploadImage.setOnClickListener{
            val gallery= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        buttonEditProfile.setOnClickListener{
            val encodedString:String =  Base64.getEncoder().encodeToString(this.imgArray)
            val name= findViewById<EditText>(R.id.input_name)
            val strEncodeImage:String = "data:image/png;base64," + encodedString
            var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
            val id=sp.getString("iduser","")
            val user = User(id,name.text.toString(),null,null,null,null,null,null,null,strEncodeImage)
            val service= ApiEngine.getApi().create(PostService::class.java)
            val result:Call<User> = service.updateUser(id.toString(),user)
            result.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                  Log.e("EROR","HOLA")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("EROR","ADIOS")

                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode== RESULT_OK && requestCode == pickImage){
            Log.e("Error",data.toString())
            val photo =  data?.data
            val image=findViewById<ImageView>(R.id.img_logo)
            image.setImageURI(photo)
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            Log.e("ERROR","AQUI")
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            imgArray =  stream.toByteArray()
        }
    }
}