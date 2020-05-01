package com.example.json

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val imagePath: String? = intent.getStringExtra("imgResult")
        if(imagePath?.isNotEmpty()!!){
            val bitmap = BitmapFactory.decodeFile(imagePath)
            images?.setImageBitmap(bitmap)
        }

        val idResult = intent.getStringExtra("idResult")
        txtIDs.setText("id: $idResult")

        val titleResult = intent.getStringExtra("titleResult")
        txtTitles.text = titleResult
    }

}
