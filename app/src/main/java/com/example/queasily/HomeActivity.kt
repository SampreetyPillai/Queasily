package com.example.queasily

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class HomeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myIntent = getIntent()
        val USERNAME:String? = myIntent.getStringExtra("USERNAME")

        findViewById<Button>(R.id.attempt_quiz).setOnClickListener{
            val newIntent = Intent(this, Questions::class.java)
            newIntent.putExtra("Time", LocalDateTime.now())
            newIntent.putExtra("qno",1)
            newIntent.putExtra("USERNAME",USERNAME)
            newIntent.putExtra("quizid","tdF963EsyAtkY5ifObtI")
            startActivity(newIntent)
        }
    }
}