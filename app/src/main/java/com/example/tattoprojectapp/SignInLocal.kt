package com.example.tattoprojectapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.tattoprojectapp.DB.SQLiteLocal
import com.example.tattoprojectapp.api.LocalService
import com.example.tattoprojectapp.databinding.SignInLocalBinding
import com.example.tattoprojectapp.models.ApiEngine
import com.example.tattoprojectapp.models.Local
import com.example.tattoprojectapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

class SignInLocal : AppCompatActivity() {
    private val pickImage = 100
    var imgArray:ByteArray? =  null
    lateinit var binding: SignInLocalBinding
    lateinit var localSQL: SQLiteLocal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_local)
        binding=SignInLocalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localSQL= SQLiteLocal(this)

        binding.btnSignin.setOnClickListener {

            createLocal()

        }
        binding.btnLoadimage.setOnClickListener{
            val gallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode== RESULT_OK && requestCode==pickImage){
            val photo=data?.data
            val imageContainer= findViewById<ImageView>(R.id.imageView3)
            Log.e("Error",photo.toString())
            imageContainer.setImageURI(photo)
            val bitmap = (imageContainer.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            imgArray =  stream.toByteArray()
        }
    }

    private fun createLocal(){
        val name=findViewById<EditText>(R.id.input_name_shop)
        val adress= findViewById<EditText>(R.id.input_adress_shop)
        val opendays= findViewById<EditText>(R.id.input_open_days_shop)
        val schedule= findViewById<EditText>(R.id.input_schedule)
        val encodedString:String =  Base64.getEncoder().encodeToString(this.imgArray)
        val strEncodeImage:String = "data:image/png;base64," + encodedString
        val sp: SharedPreferences =applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val id=sp.getString("iduser","")
        val local= Local("",id,null,adress.text.toString(),strEncodeImage,opendays.text.toString(),schedule.text.toString(), name.text.toString())
        val service = ApiEngine.getApi().create(LocalService::class.java)
        val result: Call<Local> =service.createLocal(local)
        binding=SignInLocalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localSQL=SQLiteLocal(this)
        localSQL.newLocal(name.text.toString(),adress.text.toString(),opendays.text.toString(),schedule.text.toString(),strEncodeImage)
        result.enqueue(object: Callback<Local> {
            override fun onFailure(call: Call<Local>, t: Throwable) {
                Log.e("EROR","HOLA")
            }

            override fun onResponse(call: Call<Local>, response: Response<Local>) {
                Log.e("ERROR",response.toString())
                Log.e("EROR","ADIOS")

            }
        })


    }
}