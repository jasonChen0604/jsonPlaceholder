package com.example.json.Adapter


import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.json.PhotoDataModel
import com.example.json.R

class PhotoAdapter(private var list: ArrayList<PhotoDataModel>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.txtID.text = list[position].getID()
        holder.txtTitle.text = list[position].getTitle()
        holder.px_thumbnailUrl.text = list[position].getThumbnailUrl().substring(28, 31)

        val imagePath = list[position].getUrl()
        if(imagePath.isNotEmpty()){
            val bitmap = BitmapFactory.decodeFile(imagePath)
            holder.image?.setImageBitmap(bitmap)
        }

//        holder.itemView.setOnClickListener(){
//            val content = holder.itemView.context
//            var intent = Intent(content, ItemActivity::class.java)
//            intent.putExtra("imgResult", imagePath)
//            intent.putExtra("idResult", holder.txtID.text!!)
//            intent.putExtra("titleResult", holder.txtTitle.text!!)
//            content.startActivity(intent)
//        }

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.image)
        val txtID = view.findViewById<TextView>(R.id.txtID)
        val px_thumbnailUrl = view.findViewById<TextView>(R.id.px_thumbnailUrl)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
    }

}