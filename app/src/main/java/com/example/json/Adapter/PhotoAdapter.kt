package com.example.json.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.json.R

class PhotoAdapter(private var list: ArrayList<String>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.image)
        val txtID = view.findViewById<TextView>(R.id.txtID)
        val px_thumbnailUrl = view.findViewById<TextView>(R.id.px_thumbnailUrl)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
    }

}