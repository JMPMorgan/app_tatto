package com.example.tattoprojectapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_menu)
        //var btnAppBar=findViewById<MenuItem>(R.menu.bottom_menu)
       // private lateinit var binding: ActivityApplicationBinding
        /*val btnProfile = findViewById<Button>(R.id.account)
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    // put your code here
                    val launch = Intent(this,Profile::class.java)
                    startActivity(launch)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    // put your code here
                    return@OnNavigationItemSelectedListener true
                }
                R.id.messages -> {
                    // put your code here
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favs -> {
                    // put your code here
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        //menuBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        btnProfile.setOnClickListener{
            val launch = Intent(this,Profile::class.java)
            startActivity(launch)
        }
        */
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.account->{
                val launch = Intent(this,Profile::class.java)
                startActivity(launch)
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            R.id.home -> {
                // put your code here
             true
            }
            R.id.messages->{
                true
            }
            R.id.favs->{
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}