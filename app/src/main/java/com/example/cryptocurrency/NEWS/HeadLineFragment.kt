package com.example.cryptocurrency.NEWS

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.cryptocurrency.R
import com.example.cryptocurrency.NEWS.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_headline.*

class HeadLineFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    private var newsDataList:ArrayList<NewsDATA> = ArrayList<NewsDATA>()
    private val  adapter: Adapter= Adapter(newsDataList,context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val requestQueue:RequestQueue=Volley.newRequestQueue(context)
        val view = inflater.inflate(R.layout.fragment_headline, container, false)
        var recyclerView=view.findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager=LinearLayoutManager(context)
        val mainClassNEWS=
            context?.let { MainClassNEWS(it,newsDataList,adapter,recyclerView,requestQueue) }
        if (mainClassNEWS != null) {
            mainClassNEWS.NEWSApplication("bbc-news","al-jazeera-english","google-news")
        }
        return view
    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnListFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

}
