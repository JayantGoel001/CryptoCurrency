package com.example.cryptocurrency.NEWS


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

import com.example.cryptocurrency.R
import kotlinx.android.synthetic.main.fragment_buisness.*
import kotlinx.android.synthetic.main.fragment_headline.*

class BuisnessFragment : Fragment() {
    private var newsDataList:ArrayList<NewsDATA> = ArrayList<NewsDATA>()
    private val  adapter: Adapter= Adapter(newsDataList,context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_buisness, container, false)
        val recyclerView=view.findViewById<RecyclerView>(R.id.RecyclerViewBuisness)

        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager=LinearLayoutManager(context)
        val mainClassNEWS=
            context?.let { MainClassNEWS(it,newsDataList,adapter,recyclerView,requestQueue) }

        if (mainClassNEWS != null) {
            mainClassNEWS.NEWSApplication("bloomerg","buisness-insider","cnbc")
        }
        return view
    }


}
