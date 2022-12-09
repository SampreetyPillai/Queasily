package com.example.queasily

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDateTime
import kotlin.concurrent.fixedRateTimer

class Questions : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    @RequiresApi(Build.VERSION_CODES.O)

    val logi = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        var Time:String? = LocalDateTime.now().toString()

        val currIntent = getIntent()
//        Time = currIntent.getStringExtra("Time")
//        val qno:Int = currIntent.getIntExtra("qno",0)
//        val quizid:String? = currIntent.getStringExtra("quizid")
//        val USERNAME:String? = currIntent.getStringExtra("USERNAME")
//
//        val btn2=findViewById<Button>(R.id.button)
//        btn2.setOnClickListener{
            var ms = 0
            mediaPlayer = MediaPlayer.create(this, R.raw.alarm)

            fixedRateTimer("timer", false, 0L, 1*1000){
                ms+=1
                var min=0
                var s=0
                s=ms%60
                min=(ms/60).toInt()


                var t=String.format("%02d:%02d", min, s)
                findViewById<TextView>(R.id.textView).text=t
                if(ms>=5) {
                    findViewById<TextView>(R.id.textView).text = "Time Up!!"
                    mediaPlayer.start()
                    this.cancel()

                }
            }




    }
}