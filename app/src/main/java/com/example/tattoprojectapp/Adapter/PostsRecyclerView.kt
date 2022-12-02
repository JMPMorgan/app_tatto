package com.example.tattoprojectapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.R
import com.example.tattoprojectapp.models.Post

class PostsRecyclerView(val posts:List<Post>):RecyclerView.Adapter<PostsRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_posts,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return;
    }

    override fun getItemCount(): Int {
        return posts.size;
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }
}