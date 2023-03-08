package com.example.queasily

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.collection.LLRBNode

class AnalysisActivity : AppCompatActivity() {
    var barArrayList  = arrayListOf<BarEntry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)
        getData()


        var barDataSet = BarDataSet(barArrayList, "Marks Analysis")
        var barData = BarData(barDataSet)
        var barChart: BarChart = findViewById(R.id.barchart)
        barChart.data = barData
       // barDataSet.setColors(mutableListOf(ColorTemplate.COLORFUL_COLORS) )
        barDataSet.setValueTextColor(Color.BLACK)

    }

    private fun getData(){

        barArrayList.add(BarEntry(2f,2000f))
        barArrayList.add(BarEntry(4f,4000f))
        barArrayList.add(BarEntry(6f,6000f))
        barArrayList.add(BarEntry(8f,8000f))


    }
}