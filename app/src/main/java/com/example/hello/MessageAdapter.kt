package com.example.hello

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context:Context,val messageList:ArrayList<Message>,val recId:String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val ITEM_RECEIVE=2
    val ITEM_SENT=1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==1){
            //receive
            val view=LayoutInflater.from(context).inflate(R.layout.recitem,parent,false)
            Receive(view)
        }else{
            //send
            val view =LayoutInflater.from(context).inflate(R.layout.senditem,parent,false)
            Send(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder.javaClass == Send::class.java){
            val currecnt=messageList[position]
            val viewHolder=holder as Send
            holder.sendmessage.text=currecnt.message

        }else{
            val currecnt=messageList[position]
            val viewHolder=holder as Receive
            holder.receivemessage.text=currecnt.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val id=FirebaseAuth.getInstance().currentUser?.uid+recId
        return if(id == messageList[position].sendId){
            ITEM_SENT
        }else{
            ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class Send(itemView: View):RecyclerView.ViewHolder(itemView){
        val sendmessage=itemView.findViewById<TextView>(R.id.sendMessage)
    }
    class Receive(itemView: View):RecyclerView.ViewHolder(itemView) {

        val receivemessage = itemView.findViewById<TextView>(R.id.Receive_Message)

    }


}