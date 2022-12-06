package com.example.tattoprojectapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity
import com.example.tattoprojectapp.Chats
import com.example.tattoprojectapp.Messages
import com.example.tattoprojectapp.R
import com.example.tattoprojectapp.models.Conversations
import com.squareup.picasso.Picasso

class ChatsRecyclerView(val context: Context ,val chats:List<Conversations>): RecyclerView.Adapter<ChatsRecyclerView.ViewHolder>(),Filterable{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_chat,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val chat=chats.get(position)
        if(chat.idreceiver.equals(chat.user!!.userid)){
            holder.user.text=chat.user!!.name
            Picasso.get().load(chat.user!!.file.toString()).into(holder.imgUser)
        }else{
            holder.user.text=chat.artists!!.name
            Picasso.get().load(chat.artists!!.file.toString()).into(holder.imgUser)
        }
        holder.conversationID=chat.id.toString()
        return;
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    inner class  ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgUser=itemView?.findViewById<ImageView>(R.id.imagePost)
        val user=itemView?.findViewById<TextView>(R.id.vtUser)
        var conversationID:String=""
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.chat_info->{
                    Log.e("Eroror",this.user.toString())
                    val activity=Intent(context,Messages::class.java)
                    activity.putExtra("Conversation_ID",this.conversationID)
                    context.startActivity(activity)
                }
            }
        }
    }

}