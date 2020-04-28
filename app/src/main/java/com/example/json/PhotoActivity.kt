package com.example.json

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class PhotoActivity : AppCompatActivity() {
    private val tag: String = "PhotoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        getData(this).execute()
        //rclPhoto.layoutManager = GridLayoutManager(this, 4)
    }

    class getData(private var activity: PhotoActivity?) : AsyncTask<String, String, String>() {

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
                        json.append(line);
                        line = bReader.readLine();
                    }
                }
            } catch (ex: Exception) {
                Log.d("", "Error in doInBackground " + ex.message)
            }

            return json.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try{

                val array = JSONArray(result.toString())
                for (i in 0 until array.length()) {
                    val jsonObject: JSONObject = array.getJSONObject(i)
                    val albumId = jsonObject.getString("albumId")
                    val id = jsonObject.getString("id")
                    val title = jsonObject.getString("title")
                    val url = jsonObject.getString("url")
                    val thumbnailUrl = jsonObject.getString("thumbnailUrl")

//                    photos.add(
//                        Photo(
//                            albumId, id, title, url, thumbnailUrl
//                        )
//                    )

                    Log.d(activity?.tag, "albumId:$albumId, id:$id, title:$title, url:$url, thumbnailUrl:$thumbnailUrl")
                }

                //rclPhoto.adapter = PhotoAdapter(    )


            }
            catch (ex: Exception) {
                Log.d("", "Error in onPostExecute " + ex.message)
            }

        }

    }
}

