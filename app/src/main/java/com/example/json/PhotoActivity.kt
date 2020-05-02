package com.example.json

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.json.Adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class PhotoActivity : AppCompatActivity() {
    private val tag: String = "PhotoActivity"
    private val rclPhoto: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        GlobalScope.launch(Dispatchers.Main) {
            Thread {
                //Do some Network Request
                val result = httpGet("https://jsonplaceholder.typicode.com/photos")
                runOnUiThread {
                    txtResult.text = result
                }
            }.start()
        }

        rclPhoto?.layoutManager = GridLayoutManager(this, 4)

        rclPhoto?.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            var gestureDetector = GestureDetector(this@PhotoActivity, object : GestureDetector.SimpleOnGestureListener(){
                override fun onSingleTapUp(e: MotionEvent?): Boolean {
                    return true
                }
            })
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                if (child != null && gestureDetector.onTouchEvent(e)){
                    val position = rv.getChildAdapterPosition(child)

                    val photoList = ArrayList<PhotoDataModel>()
                    val adapter = PhotoAdapter(photoList)

                    val itemID = adapter.list[position].getID()
                    val itemTitle = adapter.list[position].getTitle()
                    val itemUrl = adapter.list[position].getUrl()

                    val intent = Intent(this@PhotoActivity, ItemActivity::class.java)
                    intent.putExtra("itemID", itemID)
                    intent.putExtra("itemTitle", itemTitle)
                    intent.putExtra("itemUrl", itemUrl)

                    startActivity(intent)
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }

    private fun httpGet(myURL: String?): String {
        val json = StringBuffer()

        // create URL
        val url: URL = URL(myURL)

        // create HttpURLConnection
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        val responseCode: Int = httpURLConnection.responseCode

        if (responseCode == 200) {

            val inStream: InputStream = httpURLConnection.inputStream
            val isReader = InputStreamReader(inStream)
            val bReader = BufferedReader(isReader)
            var line = bReader.readLine()

            while (line != null) {
                json.append(line)
                line = bReader.readLine()
            }
        }        // make GET request to the given URL
        val jsonArray = JSONArray(json.toString())
        return json.toString()
    }
}
