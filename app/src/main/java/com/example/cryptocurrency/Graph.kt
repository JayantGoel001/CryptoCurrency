package com.example.cryptocurrency

import android.content.Context
import android.graphics.Color
import android.view.Display
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import org.json.JSONException
import java.lang.reflect.Array
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Graph(var lineChart:LineChart,var BarChar: BarChart) {
    fun LineGraph(context:Context,coin:String)
    {
        val entries=ArrayList<Entry>()
        val barEntry=ArrayList<BarEntry>()
        val requestQueue=Volley.newRequestQueue(context)
        val url="https://min-api.cryptocompare.com/data/histohour?fsym=${coin}&tsym=USD&limit=30&aggregate=1&e=CCCAGG"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
            try {
                val jsonArray = it.getJSONArray("Data")
                val close  = ArrayList<Int>()
                val date=ArrayList<String>()
                val volume=ArrayList<Int>()
                for(i in 0..jsonArray.length()-1)
                {
                    val jsonObject =jsonArray.getJSONObject(i)
                    close.add(jsonObject.getInt("close"))
                    date.add(jsonObject.getString("time"))
                    volume.add(jsonObject.getInt("volumeto"))
                    entries.add(Entry(date[i].toFloat(),close[i].toFloat()))
                    barEntry.add(BarEntry(date[i].toFloat(),volume[i].toFloat()))
                }
                DisplayLineGraph(entries)
                DisplayBarGraph(barEntry)
            }
            catch (e:JSONException)
            {
                e.printStackTrace()
            }
        },Response.ErrorListener {
            Toast.makeText(context,"Check Your Internet Connection",Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(jsonObjectRequest)
    }
    fun DisplayBarGraph(entry:List<BarEntry>)
    {
        val barDataSet=BarDataSet(entry,"Volume")
        val barData=BarData(barDataSet)
        barDataSet.setColor(Color.rgb(214,162,232))
        BarChar.description.text="Time : "
        BarChar.setOnChartValueSelectedListener(object : OnChartValueSelectedListener
        {
            override fun onNothingSelected() {

            }

            override fun onValueSelected(e: Entry, h: Highlight) {
                BarChar.description.text="Time : "+unix_time(e.x.toLong())

            }

        })
        BarChar.data=barData
        barData.barWidth=900f
        BarChar.setDrawGridBackground(false)
        BarChar.setBackgroundColor(Color.TRANSPARENT)
        BarChar.setDrawBorders(false)
        BarChar.axisLeft.setDrawGridLines(false)
        BarChar.setTouchEnabled(true)
        BarChar.setScaleEnabled(true)
        BarChar.animateX(2000)
        BarChar.axisRight.setDrawGridLines(false)
        val xAxis=BarChar.xAxis
        xAxis.position=XAxis.XAxisPosition.BOTTOM
        xAxis.isEnabled=true
        BarChar.description.textSize=15.0f
        BarChar.legend.isEnabled=true
        val yAxis=BarChar.axisRight
        val yAxis1=BarChar.axisLeft
        yAxis.isEnabled=false
        yAxis1.isEnabled=true
        BarChar.invalidate()

    }
    fun DisplayLineGraph(entry:ArrayList<Entry>)
    {
        val lineDataSet=LineDataSet(entry,"Close")
        val lineData=LineData(lineDataSet)
        lineDataSet.setColor(Color.rgb(27,156,252))
        lineDataSet.mode=LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.setCircleColor(Color.rgb(27,156,252))

        lineChart.description.text="Time : "
        lineChart.setOnChartValueSelectedListener(
            object :OnChartValueSelectedListener
            {
                override fun onNothingSelected() {

                }

                override fun onValueSelected(e: Entry, h: Highlight) {
                    lineChart.description.text="Time : "+unix_time(e.x.toLong())
                }

            }
        )
        lineChart.data=lineData
        lineChart.setDrawGridBackground(false)
        lineChart.setBackgroundColor(Color.TRANSPARENT)
        lineChart.setDrawBorders(false)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.setTouchEnabled(true)
        lineChart.setScaleEnabled(true)
        lineChart.animateX(2000)
        lineChart.axisRight.setDrawGridLines(false)
        val xAxis=lineChart.xAxis
        xAxis.position=XAxis.XAxisPosition.BOTTOM
        xAxis.isEnabled=true
        lineChart.description.textSize=15.0f
        lineChart.legend.isEnabled=true
        val yAxis=lineChart.axisRight
        val yAxis1=lineChart.axisLeft
        yAxis.isEnabled=false
        yAxis1.isEnabled=true
        lineChart.invalidate()

    }
    fun unix_time(timeStamp:Long):String
    {
        var date= Date(timeStamp*1000L)
        var x= SimpleDateFormat("HH:mm:");
        x.timeZone= TimeZone.getDefault()
        var formattedDate=x.format(date)
        var _24Hr= SimpleDateFormat("HH:mm")
        var _12Hr= SimpleDateFormat("hh:mm a")
        var _24HrDT: Date?=null
        try {
            _24HrDT=_24Hr.parse(formattedDate)
        }
        catch (e: ParseException)
        {
            e.printStackTrace()
        }
        return _12Hr.format(_24HrDT)
    }
}