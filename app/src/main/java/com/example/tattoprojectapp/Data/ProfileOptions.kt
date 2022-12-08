package com.example.tattoprojectapp.Data

import android.content.Context

object ProfileOptions {
    val options= ArrayList<ProfileData>()
    var context:Context?=null
    init{
        this.initializeProfileOptions()
    }

    private fun initializeProfileOptions(){
        var option=ProfileData(1,"Edit Profile")
        options.add(option)
        option= ProfileData(2,"Create Local")
        options.add(option)
        option= ProfileData(3,"Create Post")
        options.add(option)
        option= ProfileData(4,"All Posts")
        options.add(option)
        option= ProfileData(5,"Log Out")
        options.add(option)
    }
}