package com.example.cryptocurrency.NEWS


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley

import com.example.cryptocurrency.R
import kotlinx.android.synthetic.main.fragment_buisness.*
import kotlinx.android.synthetic.main.fragment_technology.*

/**
 * A simple [Fragment] subclass.
 */
class TechnologyFragment : Fragment() {
    private var newsDataList:ArrayList<NewsDATA> = ArrayList<NewsDATA>()
    private var adapter=Adapter(newsDataList,context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val requestQueue=Volley.newRequestQueue(context)
        var view=inflater.inflate(R.layout.fragment_technology, container, false)
        // Inflate the layout for this fragment
        var recyclerView=view.findViewById<RecyclerView>(R.id.RecyclerViewTechnology)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager= LinearLayoutManager(context)
        val mainClassNEWS=
            context?.let { MainClassNEWS(it,newsDataList,adapter,recyclerView,requestQueue) }
        if (mainClassNEWS != null) {
            mainClassNEWS.NEWSApplication("crypto-coins-news","engadget","hacker-news")
        }
        return view
    }


}
