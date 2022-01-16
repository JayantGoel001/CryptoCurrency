package com.example.cryptocurrency.NEWS


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cryptocurrency.R
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 */
class BITCOIN : Fragment() {
    private val news_data_list= ArrayList<NewsDATA>()
    private var adapter=Adapter(news_data_list,context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_bitcoin, container, false)
        val recycler_view=view.findViewById<RecyclerView>(R.id.RecyclerViewBitcoin)
        recycler_view.hasFixedSize()
        recycler_view.layoutManager=LinearLayoutManager(context)
        val url="https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-17&apiKey=APIKEY"
        val requestQueue=Volley.newRequestQueue(context)
        val jsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
            try
            {
                val jsonArray=it.getJSONArray("articles")
                news_data_list.clear()
                for(i in 0..jsonArray.length()-1)
                {
                    var jsonObject=jsonArray.optJSONObject(i)
                    var newsDATA=NewsDATA(jsonObject.getString("title"),jsonObject.getString("author"),jsonObject.getString("urlToImage"))
                    news_data_list.add(newsDATA)
                    adapter= Adapter(news_data_list,context)
                    recycler_view.adapter=adapter
                    adapter.notifyDataSetChanged()
                }
            }
            catch (e: JSONException)
            {
                e.printStackTrace()
            }

        }, Response.ErrorListener {
            Toast.makeText(context,"Check Your Internet Connection",Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(jsonObjectRequest)
        return view
    }


}
