package com.example.signupandsignin.Pages

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.signupandsignin.Classes.DBHelper
import com.example.signupandsignin.R

class SignIn : AppCompatActivity() {
    // Declaring UI Elements
    lateinit var etEmailorUser: EditText
    lateinit var etPassforIn: EditText
    //Declaring Variables for Database
    var emailS3 = ""
    var passwordS4 = ""
    lateinit var dbhelper: DBHelper
    var userFirstN = ""
    // Declaring Variables for Shared Preferences
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        // Initializing UI Elements
        etEmailorUser = findViewById(R.id.etEmailorUser)
        etPassforIn = findViewById(R.id.etPassforIn)
        // Initializing Variables for Database
        dbhelper = DBHelper(applicationContext)
        //Initializing Share Preferences
        sharedPreferences = getSharedPreferences(getString(R.string.preference_users), MODE_PRIVATE)
    }

    fun signingInIn(view: android.view.View) {
            emailS3 = "${etEmailorUser.text}"
            passwordS4 = "${etPassforIn.text}"
            var pass = dbhelper.retrieve(emailS3)
        if(pass.isEmpty()){
            Toast.makeText(applicationContext, "This email does not exist!", Toast.LENGTH_SHORT).show()
        }
            else if (passwordS4 != pass) {
                Toast.makeText(applicationContext, "Incorrect password!", Toast.LENGTH_SHORT).show()
            } else {
                toSharePreferences()
                val userInfo = dbhelper.retrieveAll(emailS3).toString()
                val intent = Intent(this, Details::class.java)
                intent.putExtra("userInfo", userInfo)
                startActivity(intent)
            }
    }
    fun toSharePreferences(){
        userFirstN = dbhelper.retrieveN(emailS3)
        editor = sharedPreferences.edit()
        editor.putString("userData", userFirstN)
        editor.commit()
    }
}
//app:srcCompat="@drawable/show_password"