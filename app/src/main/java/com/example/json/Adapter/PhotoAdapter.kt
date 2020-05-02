package com.example.json.Adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.json.PhotoDataModel
import com.example.json.R


class PhotoAdapter(var list:ArrayList<PhotoDataModel>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtID.text = list[position].getID()
        holder.txtTitle.text = list[position].getTitle()
        holder.px_thumbnailUrl.text = list[position].getThumbnailUrl().substring(28, 31)
        val imagePath = list[position].getUrl()
        if(imagePath.isNotEmpty()){
            val bitmap = BitmapFactory.decodeFile(imagePath)
            holder.image?.setImageBitmap(bitmap)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.image)
        var txtID = view.findViewById<TextView>(R.id.txtID)
        var px_thumbnailUrl = view.findViewById<TextView>(R.id.px_thumbnailUrl)
        var txtTitle = view.findViewById<TextView>(R.id.txtTitle)
    }
}