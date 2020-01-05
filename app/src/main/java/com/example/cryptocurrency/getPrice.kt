package com.example.cryptocurrency

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.json.JSONException
import java.lang.Error
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round

class getPrice(var Price:TextView,var High:TextView,var Low:TextView,var Open:TextView,var Volume:TextView,var tradeID:TextView,var LastUpdate:TextView,var pieChart: PieChart) {
    fun priceData(context: Context,coin:String,totalCoin: String)
    {
        val requestQueue=Volley.newRequestQueue(context)
        val url="https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=${coin}&tsym=USD"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
            try {
                val data = it.getJSONObject("Data")
                var coinInfo=data.getJSONObject("CoinInfo")
                val aggData = data.getJSONObject("AggregatedData")
                try {
                    var totalCoinMined=coinInfo.getString("TotalCoinsMined")
                    DisplayGraph(totalCoin,totalCoinMined)
                }catch (e:Error)
                {

                }


                try {
                    var x=aggData.getString("PRICE")
                    Price.text="Price : "+x
                }
                catch (e:Error)
                {
                    Price.text="Data Not Available"
                }
                try {
                    var x=aggData.getString("OPENDAY")
                    Open.text="Open : "+x
                }
                catch (e:Error)
                {
                    Open.text="Data Not Available"
                }

                try {
                    var x=aggData.getString("HIGHDAY")
                    High.text="High : "+x

                } catch (e:Error)
                {
                    High.text="Data Not Available"
                }
                try {
                    var x=aggData.getString("VOLUMEDAY")
                    Volume.text="Volume : "+ round(x.toFloat()).toString()

                } catch (e:Error)
                {
                    Volume.text="Data Not Available"
                }
                try {
                    var x=aggData.getString("LOWDAY")
                    Low.text="Close : "+x
                }
                catch (e:Error)
                {
                    Low.text="Data Not Available"
                }
                try {
                    var x=aggData.getString("LASTTRADEID")
                    tradeID.text="Last Trade ID : "+x
                }
                catch (e:Error)
                {
                    tradeID.text="Data Not Available"
                }
                try {
                    var x=aggData.getString("LASTUPDATE")
                    var convert =unix_time(x.toLong())
                    LastUpdate.text="Last Upadte : "+convert
                }
                catch (e:Error)
                {
                    LastUpdate.text="Data Not Available"
                }
            }
            catch (e:JSONException)
            {
                e.printStackTrace()
            }
        }, Response.ErrorListener {
            Toast.makeText(context,"Please Check Your Internet Connection", Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(jsonObjectRequest)
    }

    fun unix_time(timeStamp:Long):String
    {
        var date= Date(timeStamp*1000L)
        var x=SimpleDateFormat("HH:mm:");
        x.timeZone=TimeZone.getDefault()
        var formattedDate=x.format(date)
        var _24Hr=SimpleDateFormat("HH:mm")
        var _12Hr=SimpleDateFormat("hh:mm a")
        var _24HrDT:Date?=null
        try {
            _24HrDT=_24Hr.parse(formattedDate)
        }
        catch (e:ParseException)
        {
            e.printStackTrace()
        }
        return _24Hr.format(_24HrDT)
    }
    fun DisplayGraph(totalCoin:String,totalCoinMined: String)
    {
        var arrayList=ArrayList<PieEntry>()
        arrayList.add(PieEntry(totalCoin.toFloat(),"Total Coin"))
        arrayList.add(PieEntry(totalCoinMined.toFloat(),"Total Coined Mined"))
        var pieDataSet=PieDataSet(arrayList,"")
        var colors=ArrayList<Int>()
        colors.add(Color.rgb(249,127,81));
        colors.add(Color.rgb(109,37,79))
        pieDataSet.setColors(colors)


        var pieData= PieData(pieDataSet)
        pieChart.data=pieData
        pieChart.invalidate()
        pieChart.isDrawHoleEnabled=false
        pieChart.animateXY(1000,1000)
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.BLACK)


    }
}