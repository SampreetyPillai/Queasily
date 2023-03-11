package com.example.queasily

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.collection.LLRBNode
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnalysisActivity : AppCompatActivity() {
    var barArrayList  = arrayListOf<BarEntry>()
    var selectedOptions = hashMapOf<String, String>()
    var correctOptions = hashMapOf<String, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)
        getData()

        val str:String = ""


//        val myIntent = getIntent()
//        val USERNAME:String? = myIntent.getStringExtra("USERNAME")
//        val QUIZNAME:String? = myIntent.getStringExtra("QUIZNAME")
//        Log.d(TAG, "${USERNAME} analysis")
//        findViewById<TextView>(R.id.finalscore).text = "Final Score: "+str
//        val db = Firebase.firestore
//        val finalscore = 0
//
//        if (QUIZNAME != null) {
//                db.collection(QUIZNAME).get().addOnSuccessListener { result->
//                for(document in result){
//
//                    correctOptions[ document.get("q_no").toString()] = document.get("correct").toString()
//                }
//            }
//        }
//        Log.d(TAG, "insisde Analysis")
//        if(USERNAME!=null){
//            db.collection(USERNAME).get().addOnSuccessListener { result->
//
//                for(document in result){
//                    Log.d(TAG, document.id)
//                    if(document.id==QUIZNAME){
////                        for(t in document.data){
////                            Log.d(TAG,"${t} ")
////                        }
//
//                        break
//                    }
//                }
//            }
//        }




        var barDataSet = BarDataSet(barArrayList, "Marks Analysis")
        var barData = BarData(barDataSet)
        var barChart: BarChart = findViewById(R.id.barchart)
        barChart.data = barData
       barDataSet.setColor(3)
        barDataSet.setValueTextColor(Color.BLACK)

    }

    private fun getData(){

        barArrayList.add(BarEntry(1000f,200f))
        barArrayList.add(BarEntry(2000f,240f))
        barArrayList.add(BarEntry(3000f,500f))
        barArrayList.add(BarEntry(4000f,600f))


    }
}