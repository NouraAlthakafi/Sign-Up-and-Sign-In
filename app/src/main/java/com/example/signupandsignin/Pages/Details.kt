package com.example.signupandsignin.Pages

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.signupandsignin.R

class Details : AppCompatActivity() {
    // Declaring UI Elements
    lateinit var tvGreetings: TextView
    lateinit var tvDetail: TextView
    // Declaring Variables for Shared Preferences
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        // Initializing UI Elements
        tvGreetings = findViewById(R.id.tvGreetings)
        tvDetail = findViewById(R.id.tvDetail)
        //Initializing Share Preferences
        sharedPreferences = getSharedPreferences(getString(R.string.preference_users), MODE_PRIVATE)
        // Utilizing
        //val sent = intent.getStringExtra("userName")
        val sentInfo = intent.getStringExtra("userInfo").toString()
        tvGreetings.text = "Welcome, ${sharedPreferences.getString("userData", "User!").toString()}!\n Here is your account's details:"
        tvDetail.text = sentInfo
    }

    fun signingOut(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}