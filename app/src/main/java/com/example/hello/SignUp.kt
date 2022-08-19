package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val email=findViewById<EditText>(R.id.editTextGetEmail)
        val password=findViewById<EditText>(R.id.editTextGetPassword)
        val name=findViewById<EditText>(R.id.getName)
        val sign=findViewById<Button>(R.id.SignUp)
        val changeActivity=findViewById<TextView>(R.id.login_text)

        sign.setOnClickListener{
            if(email.text.toString().length<=0){
                email.error="Required"
                return@setOnClickListener
            }
            if(password.text.toString().length<=0){
                password.error="Required"
                return@setOnClickListener
            }
            if(name.text.toString().length<=0){
                name.error="Required"
                return@setOnClickListener
            }

            //here login method of firebase
        }

        changeActivity.setOnClickListener{

            //here onclick listner
        }
    }
}