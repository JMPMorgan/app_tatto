package com.example.tattoprojectapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class SQLitehealper(context: Context): SQLiteOpenHelper(context,"usuarios.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val datos="CREATE TABLE USUARIOS" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "LASTNAME TEXT," +
                "USSER TEXT," +
                "BIRTHDATE TEXT," +
                "EMAIL TEXT," +
                "PWD TEXT)"
        db!!.execSQL(datos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val borrar ="DROP TABLE IF EXISTS USUARIOS"
        db!!.execSQL(borrar)
        onCreate(db)
    }
    fun newDato(name:String,lastname:String,usser:String,brith:String,email:String,pwd:String){
        val datos = contentValuesOf();
        datos.put("NAME",name )
        datos.put("LASTNAME",lastname )
        datos.put("USSER",usser )
        datos.put("BIRTHDATE",brith )
        datos.put("EMAIL",email )
        datos.put("PWD",pwd )
        val db= this.writableDatabase
        db.insert("USUARIOS",null,datos)
        db.close()

    }

    fun newDato2(){
        val datos = contentValuesOf();
        datos.put("ID",10)
        datos.put("NAME","gil" )
        datos.put("LASTNAME","gil2" )
        datos.put("USSER","usser" )
        datos.put("BIRTHDATE","10/12/22" )
        datos.put("EMAIL","gil@correo.com" )
        datos.put("PWD","12345678" )
        val db= this.writableDatabase
        db.insert("USUARIOS",null,datos)
        db.close()

    }
}