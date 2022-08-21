package com.example.hello

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Chat : AppCompatActivity() {
    lateinit var msg:EditText
    lateinit var send:Button
    lateinit var view:RecyclerView
    lateinit var messageList:ArrayList<Message>
    lateinit var adapter:MessageAdapter




     var receiverRoom:String?=null
     var senderRoom:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        val uid=intent.getStringExtra("uid")

        senderRoom=uid+FirebaseAuth.getInstance().currentUser?.uid.toString()
        receiverRoom=FirebaseAuth.getInstance().currentUser?.uid.toString()+uid

        msg=findViewById(R.id.message)
        send=findViewById(R.id.send)
        view=findViewById(R.id.chatList)

        messageList=ArrayList()
        view.layoutManager=LinearLayoutManager(this)
        adapter= MessageAdapter(this,messageList,uid.toString())
        view.adapter=adapter
        fetchData()



        send.setOnClickListener{
            if(msg.text.toString().length<=0){
                msg.error="Required"
                return@setOnClickListener
            }
            sendData(msg.text.toString())
        }

    }

    private fun fetchData(){
        messageList.clear()
        FirebaseDatabase.getInstance().getReference().child("chat").child(senderRoom!!).child("message").
                addValueEventListener(object: ValueEventListener{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onDataChange(snapshot: DataSnapshot) {
                        messageList.clear()
                        for (i in snapshot.children){
                            val message=i.getValue(Message::class.java) as Message
                            messageList.add(message)

                        }

                        adapter.notifyDataSetChanged()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@Chat, "something went wrong", Toast.LENGTH_SHORT).show()
                    }
                })



    }

    private fun sendData(msgs:String){
        val messageObj=Message(msgs,senderRoom)
        FirebaseDatabase.getInstance().getReference().child("chat").child(senderRoom!!).child("message")
            .push().setValue(messageObj).addOnSuccessListener {

                FirebaseDatabase.getInstance().getReference().child("chat").child(receiverRoom!!).child("message")
                    .push().setValue(messageObj)

                msg.setText("")
            }
    }
}