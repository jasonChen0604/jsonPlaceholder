package com.example.json

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray

class PhotoActivity : AppCompatActivity() {
    var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

     //   getData(this).execute()
        //rclPhoto.layoutManager = GridLayoutManager(this, 3)
        //rclPhoto.adapter = PhotoAdapter(    )
    }

    class getData(private var activity: PhotoActivity?) : AsyncTask<String, String, String>() {

        val data = "https://jsonplaceholder.typicode.com/photos"
        override fun doInBackground(vararg params: String?): String {
            TODO("Not yet implemented")
        }

        //rclPhoto.layoutManager = GridLayoutManager(this, 4)

        //rclPhoto.adapter = PhotoAdapter()
    }
}

