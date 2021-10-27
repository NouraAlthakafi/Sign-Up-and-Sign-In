package com.example.signupandsignin.Pages

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.signupandsignin.Classes.DBHelper
import com.example.signupandsignin.R

class SignUp : AppCompatActivity() {
    // Declaring UI Elements
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etUserName: EditText
    lateinit var tvEmailShow: TextView
    lateinit var etUpPass: EditText
    lateinit var etUpCheckPass: EditText
    lateinit var cbShowPass: CheckBox
    // Declaring Variables for Database
    var firstNameS1 = ""
    var lastNameS2 = ""
    var emailS3 = ""
    var passwordS4 = ""
    lateinit var dbhelper: DBHelper
    // Declaring Variables for Shared Preferences
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        // Initializing UI Elements
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etUserName = findViewById(R.id.etUserName)
        tvEmailShow = findViewById(R.id.tvEmailShow)
        etUpPass = findViewById(R.id.etUpPass)
        etUpCheckPass = findViewById(R.id.etUpCheckPass)
        cbShowPass = findViewById(R.id.cbShowPass)
        //Initializing Share Preferences
        sharedPreferences = getSharedPreferences(getString(R.string.preference_users), MODE_PRIVATE)

        // Calling methods
    }
    fun signInInstead(view: android.view.View){
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }

    fun nextDoSave(view: android.view.View) {
        if(etUserName.text.isNotEmpty()){
            toShowEmail()
        }
        if(etFirstName.text.isNotEmpty()
            && etLastName.text.isNotEmpty()
            && etUserName.text.isNotEmpty()
            && checkPassword()){
            toSaveDo()
            toSharePreferences()
            val userInfo = "${etFirstName.text}\n ${etLastName.text}\n ${etUserName.text}"
            val intent = Intent(this, Details::class.java)
            intent.putExtra("userInfo", userInfo)
            startActivity(intent)
        }
        else{
            alertEmpty()
        }
    }

    fun toShowEmail(){
        etUserName.text.toString()
        tvEmailShow.text = "${etUserName.text}@gmail.com"
    }

    fun checkPassword(): Boolean {
        var passMain = "${etUpPass.text}"
        var passConfirm = "${etUpCheckPass.text}"
        if(passConfirm != passMain){
            alertPassword()
            return false
        }
        return true
    }

    fun toSaveDo(){
        firstNameS1 = "${etFirstName.text}"
        lastNameS2 = "${etLastName.text}"
        emailS3 = "${etUserName.text}@gmail.com"
        passwordS4 = "${etUpPass.text}"
        dbhelper = DBHelper(applicationContext)
        dbhelper.savedata(firstNameS1, lastNameS2, emailS3, passwordS4)
    }

    fun toSharePreferences(){
        editor = sharedPreferences.edit()
        editor.putString("userData", "${etFirstName.text}")
        editor.commit()
    }
    fun alertPassword(){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("Password is not confirmed correctly!")
            .setCancelable(false)
            .setPositiveButton("Okay", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val warn = builder1.create()
        warn.setTitle("Warning")
        warn.show()
    }
    fun alertEmpty(){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("One or two fields are empty!")
            .setCancelable(false)
            .setPositiveButton("Okay", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val warn = builder1.create()
        warn.setTitle("Warning")
        warn.show()
    }
}
