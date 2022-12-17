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
import com.example.tattoprojectapp.DB.SQLitepost
import com.example.tattoprojectapp.api.LocalService
import com.example.tattoprojectapp.api.PostService
import com.example.tattoprojectapp.api.PostsCreationService
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Local
import com.example.tattoprojectapp.models.Post
import com.example.tattoprojectapp.databinding.NewPostBinding
import com.example.tattoprojectapp.models.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

class NewPost : AppCompatActivity() {
    private val pickImage = 100
    var imgArray:ByteArray? =  null
    var userHasLocal:Boolean?=false
    var idlocal:String?=null
    lateinit var binding: NewPostBinding
    lateinit var postSQL:SQLitepost
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_post)
        binding= NewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postSQL= SQLitepost(this)

        binding.btnPublish.setOnClickListener{
            createNewPost()
        }
        binding.btnUpload.setOnClickListener{
            val gallery= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        checkLocal()
    }

    private fun createNewPost(){
        val description=findViewById<EditText>(R.id.input_description_post)
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        if(userHasLocal==false  ){
            Toast.makeText(this,"Usuario No tiene Registrado un Local", Toast.LENGTH_SHORT).show()
            return;
        }
        val service: PostsCreationService = ApiEngine.getApi().create(PostsCreationService::class.java)
        val encodedString:String =  Base64.getEncoder().encodeToString(this.imgArray)
        val strEncodeImage:String = "data:image/png;base64," + encodedString
        val post = Post("",id, description.text.toString(),idlocal,strEncodeImage)
        binding= NewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postSQL= SQLitepost(this)
        postSQL.newPost(strEncodeImage,description.text.toString(),id.toString())
        val response: Call<Post> = service.createPost(post)
        response.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",response.toString())
                Log.e("Error","ADIOS")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("Error","HOLA")
            }
        })


    }

    private  fun checkLocal(){
        var sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val userService: LocalService= ApiEngine.getApi().create(LocalService::class.java)
        val responseLocal:Call<Local> = userService.getLocalPerUser(id!!.toString())
        responseLocal.enqueue(object :Callback<Local>{
            override fun onFailure(call: Call<Local>, t: Throwable) {
                Log.e("ERROR",t.toString())
                Log.e("ERROR",responseLocal.toString())
                Log.e("Error","ADIOS")
            }
            override fun onResponse(call: Call<Local>, response: Response<Local>) {
                Log.e("Error",response.toString())
                Log.e("Error",response.body().toString())
                if(response.body()!!.id !=null){
                    userHasLocal=true;
                    idlocal=response.body()!!.id
                }
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