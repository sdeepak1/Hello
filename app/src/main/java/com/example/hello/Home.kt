package com.example.hello

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class Home : AppCompatActivity() {
    lateinit var view:RecyclerView
    lateinit var userList:ArrayList<User>
    lateinit var adapter: Adapter
    lateinit var auth:FirebaseAuth
    lateinit var mRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth= FirebaseAuth.getInstance()
        userList= ArrayList()
        adapter= Adapter(this,userList)
        view=findViewById<RecyclerView>(R.id.view)
        view.layoutManager=LinearLayoutManager(this)
        view.adapter=adapter

        fetchData()


    }

    private fun fetchData(){
        var j=0;
        userList.clear()
        mRef=FirebaseDatabase.getInstance().getReference()
        mRef.child("User").addValueEventListener(object:ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children){
                 val name=postSnapshot.child("name").value.toString()
                    val email=postSnapshot.child("email").value.toString()
                    val uid=postSnapshot.child("uid").value.toString()

                    if(auth.currentUser?.uid != uid){
                        userList.add(User(name,email,uid))

                    }

                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Home, "something went wrong", Toast.LENGTH_SHORT).show()
            }


        })
    }
}