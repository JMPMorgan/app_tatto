package com.example.tattoprojectapp.Adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.R
import com.example.tattoprojectapp.models.Post
import com.squareup.picasso.Picasso

class PostsRecyclerView(val posts:List<Post>):RecyclerView.Adapter<PostsRecyclerView.ViewHolder>(),Filterable {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_posts,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post= posts.get(position)
        holder.txtTitle.text=post.description
        holder.postPosition=position
        Picasso.get().load(post.img.toString()).into(holder.imgPost)
        return;
    }

    override fun getItemCount(): Int {

        return posts.size;
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val txtTitle= itemView?.findViewById<TextView>(R.id.txtTitle)
        val imgPost=itemView?.findViewById<ImageView>(R.id.imgAlbumCard)
        var postPosition=0

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.cardview_post->{
                    Log.e("HOLA",v!!.id.toString())
                }
            }
        }

    }




}