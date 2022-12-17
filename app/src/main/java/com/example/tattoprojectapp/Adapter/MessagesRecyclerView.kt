package com.example.tattoprojectapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.R
import com.example.tattoprojectapp.models.Message
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MessagesRecyclerView(val messages:ArrayList<Message>,val userid:String):RecyclerView.Adapter<MessagesRecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_message,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesRecyclerView.ViewHolder, position: Int) {
        val message=messages.get(position)
        Log.e("POSITION",position.toString())
        Log.e("Message",message.toString())
        val sender:String = if(message.sender == null) message.idsender.toString() else message.sender!!.userid.toString()

        if(userid.equals(sender)){
            holder.userText.text=message.sender!!.name.toString()
        }else{
            holder.userText.text=message.sender!!.name.toString()
        }
        holder.messageText.text=message.message.toString()
        Picasso.get().load(message.sender!!.file.toString()).into(holder.imageProfile)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imageProfile=itemView?.findViewById<ImageView>(R.id.imageProfile)
        val userText= itemView?.findViewById<TextView>(R.id.user_title)
        val messageText=itemView?.findViewById<TextView>(R.id.message_user)
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }
}