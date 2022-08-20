package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val email=findViewById<EditText>(R.id.editTextGetEmail)
        val password=findViewById<EditText>(R.id.editTextGetPassword)
        val name=findViewById<EditText>(R.id.getName)
        val sign=findViewById<Button>(R.id.SignUp)
        val changeActivity=findViewById<TextView>(R.id.login_text)

        mAuth= FirebaseAuth.getInstance()

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
           signUp(email.text.toString(), password.text.toString())
            //here login method of firebase
        }

        changeActivity.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(email:String ,password:String){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task -> if(task.isSuccessful){
                val userId=mAuth.currentUser
            Toast.makeText(this, "account created successfully", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,MainActivity::class.java)
            mAuth.signOut()
            startActivity(intent)
        }else{
            //login failed
            Toast.makeText(this, "registration failed", Toast.LENGTH_SHORT).show()

        }
        }
    }
}