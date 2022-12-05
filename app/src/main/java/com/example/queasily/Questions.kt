package com.example.queasily

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDateTime

class Questions : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)

    val logi = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        var Time:String? = LocalDateTime.now().toString()

        val currIntent = getIntent()
        Time = currIntent.getStringExtra("Time")
        val qno:Int = currIntent.getIntExtra("qno",0)
        val quizid:String? = currIntent.getStringExtra("quizid")
        val USERNAME:String? = currIntent.getStringExtra("USERNAME")

//
//       logi.collection("mail").add(hashMapOf("USERNAME" to "Sampreety" ))



    }
}