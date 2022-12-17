package com.example.tattoprojectapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class SQLiteLocal (context: Context): SQLiteOpenHelper(context,"local.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?){
        val datos="CREATE TABLE LOCALES" + "(ID INTEGER PRIMARY KEY  AUTOINCREMENT," +
                "NAME TEXT," +
                "ADRESS TEXT," +
                "DAYS TEXT," +
                "SCHEDULE TEXT," +
                "IMG TEXT)"
        db!!.execSQL(datos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val borrar ="DROP TABLE IF EXISTS LOCALES"
        db!!.execSQL(borrar)
        onCreate(db)
    }

    fun newLocal(name:String,adress:String,days:String,schedule:String,img:String){
        val datos = contentValuesOf();

        datos.put("NAME",name )
        datos.put("ADRESS",adress )
        datos.put("DAYS",days )
        datos.put("SCHEDULE",schedule )
        datos.put("IMG",img )
        val db= this.writableDatabase
        db.insert("LOCALES",null,datos)
        db.close()

    }
}