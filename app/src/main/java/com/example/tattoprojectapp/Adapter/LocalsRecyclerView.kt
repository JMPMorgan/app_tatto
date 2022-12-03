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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tattoprojectapp.AllPosts
import com.example.tattoprojectapp.R
import com.example.tattoprojectapp.models.Local
import com.squareup.picasso.Picasso

class LocalsRecyclerView(val context: Context, val locals:List<Local>): RecyclerView.Adapter<LocalsRecyclerView.ViewHolder>(),
    Filterable {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_local,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = locals.get(position)
        holder.localName.text=local.name
        holder.descriptionLocal.text=local.weekdays+" "+local.location
        holder.localPosition=position;
        holder.localID=local.id.toString()
        Picasso.get().load(local.img.toString()).into(holder.imgLocal)
        return;
    }

    override fun getItemCount(): Int {
        return locals.size;
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }



    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val localName   = itemView?.findViewById<TextView>(R.id.title_local)
        val descriptionLocal= itemView?.findViewById<TextView>(R.id.description_post)
        val imgLocal= itemView?.findViewById<ImageView>(R.id.imagePost)
        var localPosition:Int=0;
        var localID:String="";
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.local_info->{

                    Log.e("POSITIO",this.localPosition.toString())
                    Log.e("ID LOCAL",this.localID)
                    val activity= Intent(context, AllPosts::class.java)
                    activity.putExtra("Local_ID",this.localID)
                    context.startActivity(activity)
                }
            }
        }

    }
}