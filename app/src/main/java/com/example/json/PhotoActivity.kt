package com.example.json

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.json.Adapter.PhotoAdapter
import com.example.json.PhotoActivity.getData.AsyncResponse
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class PhotoActivity : AppCompatActivity() {
    private val tag: String = "PhotoActivity"
    private val rclPhoto: RecyclerView? = null

    var asyncTask: AsyncTask<String, String, String>? = getData(object : AsyncResponse {
        override fun processFinish(output: ArrayList<PhotoDataModel>) {
            rclPhoto?.adapter = PhotoAdapter(output)
        }
    }).execute()

    /* Coroutines 1
    private var job: Job? = null
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        /* Coroutines 2
        job = GlobalScope.launch(Dispatchers.IO) {
            // Do something in the background
        }*/

        getData(this).execute()
        rclPhoto?.layoutManager = GridLayoutManager(this, 4)

        rclPhoto?.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("Not yet implemented")
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                TODO("Not yet implemented")
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("Not yet implemented")
            }
            //var intent = Intent(this, ItemActivity::class.java)
            //intent.putExtra("imgResult", imagePath)
            //intent.putExtra("idResult", holder.txtID.text!!)
            //intent.putExtra("titleResult", holder.txtTitle.text!!)
            //content.startActivity(intent)

        })
    }

    /* Coroutines 3
    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }*/

    class getData(private var activity: PhotoActivity) : AsyncTask<String, String, String>() {
        constructor(activity: PhotoActivity.getData.AsyncResponse) : this(PhotoActivity()) ////////


        interface AsyncResponse {
            fun processFinish(output: ArrayList<PhotoDataModel>)
        }

        var delegate: AsyncResponse? = null

        fun getData(delegate: AsyncResponse?) {
            this.delegate = delegate
        }

        val photoList = ArrayList<PhotoDataModel>()
//        var delegate: AsyncResponse? = null
//        fun constructor(delegate: AsyncResponse?) {
//            this.delegate = delegate
//        }
        override fun doInBackground(vararg params: String?): String {
            val json = StringBuffer()
            try {
                val url = URL("https://jsonplaceholder.typicode.com/photos")
                val httpURLConnection = url.openConnection() as HttpURLConnection

//                httpURLConnection.requestMethod = "GET"
//                httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0")
//                httpURLConnection.connect()

                val responseCode: Int = httpURLConnection.responseCode
                Log.d(activity?.tag, "responseCode - " + responseCode)

                if (responseCode == 200) {

                    val inStream: InputStream = httpURLConnection.inputStream
                    val isReader = InputStreamReader(inStream)
                    val bReader = BufferedReader(isReader)
                    var line = bReader.readLine()

                    while (line != null) {
                        json.append(line)
                        line = bReader.readLine()
                    }
                }
            } catch (ex: Exception) {
                Log.d("", "Error in doInBackground " + ex.message)
            }

            return json.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
//            val photoList = ArrayList<String>()
//            val photoList = ArrayList<String>()
            val photoList: ArrayList<PhotoDataModel> = ArrayList()
            try{
                val array = JSONArray(result.toString())
                for (i in 0 until array.length()) {
                    val jsonObject: JSONObject = array.getJSONObject(i)
                    val albumId = jsonObject.getString("albumId")
                    val id = jsonObject.getString("id")
                    val title = jsonObject.getString("title")
                    val url = jsonObject.getString("url")
                    val thumbnailUrl = jsonObject.getString("thumbnailUrl")

                    val photo = PhotoDataModel(albumId, id, title, url, thumbnailUrl)
//                    photoList.add(photo.toString())
//
//                    Log.d(activity?.tag, "albumId:$albumId, id:$id, title:$title, url:$url, thumbnailUrl:$thumbnailUrl")
//                }
//
//                activity?.rclPhoto?.adapter = PhotoAdapter(photoList)

                    this.photoList.add(photo)

                    Log.d(activity?.tag, "albumId:$albumId, id:$id, title:$title, url:$url, thumbnailUrl:$thumbnailUrl")
                }
                delegate?.processFinish(photoList)
                //activity?.rclPhoto?.adapter = PhotoAdapter(photoList)
            }
            catch (ex: Exception) {
                Log.d("", "Error in onPostExecute " + ex.message)
            }

        }

    }

}

//interface AsyncResponse {
//    fun processFinish(output: ArrayList<PhotoDataModel>)
//}

