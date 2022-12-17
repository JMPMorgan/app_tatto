package com.example.tattoprojectapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class SQLitepost(context: Context): SQLiteOpenHelper(context,"posts.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?){
        val datos="CREATE TABLE POSTS" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CONTENT TEXT," +
                "IMG TEXT" +
                "ID_USSER INTEGER)"
        db!!.execSQL(datos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val borrar ="DROP TABLE IF EXISTS POSTS"
        db!!.execSQL(borrar)
        onCreate(db)
    }
    fun newPost(imagen:String,content:String,idu:String){
        val datos = contentValuesOf();

        datos.put("IMG ",imagen)
        datos.put("CONTENT",content )
        datos.put("ID_USSER",idu)
        val db= this.writableDatabase
        db.insert("POSTS",null,datos)
        db.close()

    }


}