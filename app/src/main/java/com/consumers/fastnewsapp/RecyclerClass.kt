package com.consumers.fastnewsapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class RecyclerClass(val authorList : ArrayList<String>,val descriptionList : ArrayList<String>,val publisherList : ArrayList<String>
, val urlImageList : ArrayList<String>, val url : ArrayList<String>, val contentList : ArrayList<String>, val titleList : ArrayList<String>) : RecyclerView.Adapter<RecyclerClass.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_card_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = descriptionList[position]
        holder.card.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url[position]))
            it.context.startActivity(intent)
        }
        Picasso.get().load(urlImageList[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return descriptionList.size

    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView
        val text : TextView
        val card : CardView
        init {
            image = itemView.findViewById(R.id.imageView)
            text = itemView.findViewById(R.id.textView)
            card = itemView.findViewById(R.id.cardView)
        }

    }
}