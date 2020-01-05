package com.example.cryptocurrency

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.round

class Exchange(var value: EditText,var answer: TextView) {
    fun coin_exchange(context: Context,Coin:String)
    {
        val requestQueue=Volley.newRequestQueue(context)
        val url="https://min-api.cryptocompare.com/data/price?fsym=${Coin}&tsyms=USD"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
            try {
                val newCurrency = it.getInt("USD")
                val number=value.text.toString().toFloat()
                val result= (number*newCurrency)
                answer.text=result.toString()
                //Log.i("HELLLOOO","${result}")
            }
            catch (e:JSONException)
            {
                e.printStackTrace()
                //Log.i("DELL","ERR")
            }
        },Response.ErrorListener {

        })
        requestQueue.add(jsonObjectRequest)
    }

}