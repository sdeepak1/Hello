package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moveToRegister=findViewById<TextView>(R.id.register)
        val login=findViewById<Button>(R.id.login)
        val getEmail=findViewById<EditText>(R.id.editTextEmail)
        val getPassword=findViewById<EditText>(R.id.editTextPassword)

        mAuth= FirebaseAuth.getInstance()
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
            login(getEmail.text.toString(),getPassword.text.toString())
        }


        moveToRegister.setOnClickListener{
          val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }


    }

    private fun login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task -> if(task.isSuccessful){
            Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,Home::class.java)
            startActivity(intent)
                //login suucersfully
        }else{
            //login failed
        }
        }
    }
}

