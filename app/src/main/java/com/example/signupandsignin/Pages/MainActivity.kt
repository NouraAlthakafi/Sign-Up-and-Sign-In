package com.example.signupandsignin.Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signupandsignin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signingIn(view: android.view.View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
    fun signingUp(view: android.view.View) {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}