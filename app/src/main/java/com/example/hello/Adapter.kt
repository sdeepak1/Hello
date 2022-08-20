package com.example.hello

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Adapter(val context:Context,val userList: ArrayList<User>): RecyclerView.Adapter<Adapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.useritem,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current=userList[position].name
        holder.setData(current.toString())
    }

    override fun getItemCount(): Int {
      return userList.size

    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name=itemView.findViewById<TextView>(R.id.UserName)

        fun setData(names:String){
            name.text=names
        }
    }


}