package com.example.cryptocurrency.NEWS

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainClassNEWS(var context: Context,var newsDATAList: ArrayList<NewsDATA>,var adapter: Adapter,var recyclerView: RecyclerView,var requestQueue: RequestQueue)
{


    fun NEWSApplication(source1:String,source2:String,source3:String)
    {
        var url:String="https://newsapi.org/v2/top-headlines?sources=${source1},${source2},${source3}&apiKey=APIKEY"
        requestQueue=Volley.newRequestQueue(context)
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
            try
            {
                val jsonArray=it.getJSONArray("articles")
                newsDATAList.clear()
                for(i in 0..jsonArray.length()-1)
                {
                    var jsonObject=jsonArray.optJSONObject(i)
                    var newsDATA=NewsDATA(jsonObject.getString("title"),jsonObject.getString("author"),jsonObject.getString("urlToImage"))
                    newsDATAList.add(newsDATA)
                    adapter= Adapter(newsDATAList,context)
                    recyclerView.adapter=adapter
                    adapter.notifyDataSetChanged()
                }
            }
            catch (e:JSONException)
            {
                e.printStackTrace()
            }

        },Response.ErrorListener {
        })
        requestQueue.add(jsonObjectRequest)
    }
}