package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray

class PhotoActivity : AppCompatActivity() {
    var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

      //  getData(this).execute()
        //rclPhoto.layoutManager = GridLayoutManager(this, 3)
        //rclPhoto.adapter = PhotoAdapter(    )
    }

    private fun getData() {
        val data = "https://jsonplaceholder.typicode.com/photos"

        //rclPhoto.layoutManager = GridLayoutManager(this, 4)

        //rclPhoto.adapter = PhotoAdapter()


    }
}

