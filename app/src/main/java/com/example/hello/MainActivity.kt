package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moveToRegister=findViewById<TextView>(R.id.register)
        val login=findViewById<Button>(R.id.login)
        val getEmail=findViewById<EditText>(R.id.editTextEmail)
        val getPassword=findViewById<EditText>(R.id.editTextPassword)
        login.setOnClickListener{
            if(getEmail.text.toString().length<=0){
                getEmail.error = "Required"
                return@setOnClickListener
            }

            if(getPassword.text.toString().length<=0){
                getPassword.error="Required"
                return@setOnClickListener
            }
            //here login function
        }
        moveToRegister.setOnClickListener{
         // val intent= Intent(this,SignUp::class.java)
        }


    }
}

