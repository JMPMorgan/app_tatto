package com.example.tattoprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tattoprojectapp.Fragments.Profile
import com.google.android.material.bottomnavigation.BottomNavigationView

class PruebaActivity : AppCompatActivity() {

    private val profileFragment=Profile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
        val botom=findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}