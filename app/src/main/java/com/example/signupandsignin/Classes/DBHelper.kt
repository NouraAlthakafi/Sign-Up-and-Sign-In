package com.example.signupandsignin.Classes

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "details.db", null, 1){
    var sqLiteDatabase : SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?){
        if(db != null){
            db.execSQL("create table users (FirstName text, LastName text, Email text, Password text)")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
    fun savedata(firstNameS1: String, lastNameS2: String, emailS3: String, passwordS4: String): Long{
        val cv = ContentValues()
        cv.put("FirstName", firstNameS1)
        cv.put("LastName", lastNameS2)
        cv.put("Email", emailS3)
        cv.put("Password", passwordS4)
        var status = sqLiteDatabase.insert("users", null, cv)
        return status
    }

    @SuppressLint("Range")
    fun retrieve(emailS3: String): String{
        var c : Cursor = sqLiteDatabase.query("users", null, "Email = ?", arrayOf(emailS3), null, null, null)
        if(c.count<1){
            return ""
        }
        c.moveToFirst()
        var pass = c.getString(c.getColumnIndex("Password"))
        return pass
    }

    @SuppressLint("Range")
    fun retrieveN(emailS3: String): String{
        var c : Cursor = sqLiteDatabase.query("users", null, "Email = ?", arrayOf(emailS3), null, null, null)
        c.moveToFirst()
        var fina = c.getString(c.getColumnIndex("FirstName"))
        return fina
    }

    @SuppressLint("Range")
    fun retrieveAll(emailS3: String): ArrayList<String> {
        var c : Cursor = sqLiteDatabase.query("users", null, "Email = ?", arrayOf(emailS3), null, null, null)
        c.moveToFirst()
        var infoArray = arrayListOf<String>()
        infoArray.add(c.getString(c.getColumnIndex("FirstName")))
        infoArray.add(c.getString(c.getColumnIndex("LastName")))
        infoArray.add(c.getString(c.getColumnIndex("Email")))
        return infoArray
    }
}