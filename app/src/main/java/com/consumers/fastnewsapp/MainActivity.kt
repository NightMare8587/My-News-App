package com.consumers.fastnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONObject
import org.json.JSONTokener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show()
        val authorList = ArrayList<String>()
        val titleList = ArrayList<String>()
        val descriptionList = ArrayList<String>()
        val urlList = ArrayList<String>()
        val urlImageList = ArrayList<String>()
        val publishedDateList = ArrayList<String>()
        val contentList = ArrayList<String>()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.INVISIBLE
        val requestQueue = Volley.newRequestQueue(this)
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"

        val request = StringRequest(Request.Method.GET,url,{ response ->
                Log.i("info",response.toString())
            val jsonObject = JSONTokener(response).nextValue() as JSONObject

            val jsonArray = jsonObject.getJSONArray("articles")
            Log.i("get",jsonArray.toString())

            for(i in 0 until jsonArray.length()){
                Log.i("source",jsonArray.getString(i))
                val data = jsonArray.getJSONObject(i)
                Log.i("mainSource",data.getString("author"))
                authorList.add(data.getString("author"))
                titleList.add(data.getString("title"))
                descriptionList.add(data.getString("description"))
                contentList.add(data.getString("content"))
                urlList.add(data.getString("url"))
                urlImageList.add(data.getString("urlToImage"))
                publishedDateList.add(data.getString("publishedAt"))
            }

            val adap = RecyclerClass(authorList,descriptionList,publishedDateList,urlImageList,urlList,contentList,titleList)

            recyclerView.adapter = adap

        }){

        }

        requestQueue.add(request)

    }




}