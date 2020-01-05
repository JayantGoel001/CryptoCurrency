package com.example.cryptocurrency.NEWS

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.squareup.picasso.Picasso

class Adapter(var news_list:List<NewsDATA>,var context:Context?): RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =news_list.size

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        var newsDATA=news_list.get(position)
        holder.newsTitle.text=newsDATA.Title
        holder.newsAuthor.text=newsDATA.Author
        Picasso.get().load(newsDATA.ImageId).into(holder.imageView)



    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var imageView=itemView.findViewById<ImageView>(R.id.image)
        var newsTitle=itemView.findViewById<TextView>(R.id.title)
        var newsAuthor=itemView.findViewById<TextView>(R.id.author)
    }

}