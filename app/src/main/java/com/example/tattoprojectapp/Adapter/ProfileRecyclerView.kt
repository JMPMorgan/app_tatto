package com.example.tattoprojectapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.*
import com.example.tattoprojectapp.Data.ProfileData

class ProfileRecyclerView(val context:Context,val options:ArrayList<ProfileData>): RecyclerView.Adapter<ProfileRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileRecyclerView.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_optionsprofile,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileRecyclerView.ViewHolder, position: Int) {
        val option= options.get(position)
        holder.txt.text=option.option
        holder.optionPosition=option.id
        return
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val txt=itemView?.findViewById<TextView>(R.id.text_options)
        var optionPosition:Int=0;

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var launch:Intent?=null
            when(optionPosition){
                1->{
                    launch= Intent(context,EditProfile::class.java)
                }
                2->{
                    launch= Intent(context,SignInLocal::class.java)
                }
                3->{
                    launch= Intent(context,NewPost::class.java)
                }
                4->{
                    launch= Intent(context,AllPosts::class.java)
                }
                5->{
                    launch=Intent(context,SignUpActivity::class.java)
                }
            }
            context.startActivity(launch)
        }



    }
}