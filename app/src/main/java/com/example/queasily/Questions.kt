package com.example.queasily

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.recreate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDateTime
import kotlin.concurrent.fixedRateTimer

class Questions : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    @RequiresApi(Build.VERSION_CODES.O)
    private var layoutManager: RecyclerView.LayoutManager?= null
    private lateinit var questionArrayList: ArrayList<question_data>
    private var adapter: RecyclerView.Adapter<quizQuestions.ViewHolder>? = null
    val logi = Firebase.firestore
    private var quiz_name = ""
    public var qna: MutableMap<String, String> = mutableMapOf()
    val mydb = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        var Time:String? = LocalDateTime.now().toString()

        val currIntent = getIntent()
        val QUIZNAME:String? = currIntent.getStringExtra("QUIZNAME")
        if (QUIZNAME != null) {
            quiz_name = QUIZNAME
        }
        val QUIZTEACHER:String? =currIntent.getStringExtra("QUIZTEACHER")
        val QUIZPUBLISH:String? =currIntent.getStringExtra("QUIZPUBLISH")
        val QUIZEND:String? =currIntent.getStringExtra("QUIZEND")
        val QUIZDURATION:String? =currIntent.getStringExtra("QUIZDURATION")
        //val USERNAME:String? = currIntent.getStringExtra("USERNAME")

        //qna["0"] = "1"
        Log.d(TAG, "The timings for the quiz are ${QUIZPUBLISH} and ${QUIZEND}")

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
                if(ms>=8) {
                    findViewById<TextView>(R.id.textView).text = "Time Up!!"
                    mediaPlayer.start()
                   // Log.d(TAG,"time is up  ${THE_USERNAME}")
                    if (THE_USERNAME != null) {
                        submitExam(THE_USERNAME)
                    }
                    this.cancel()

                }
            }
        questionArrayList = arrayListOf()

        layoutManager = LinearLayoutManager(this)

        val rec:RecyclerView = findViewById(R.id.questions_list)

        rec.layoutManager = layoutManager

        adapter = quizQuestions(questionArrayList,this)

        rec.adapter = adapter



        EventChangeListener()

        findViewById<Button>(R.id.submit_button).setOnClickListener(){
            if (THE_USERNAME != null) {
                submitExam(THE_USERNAME)
            }

        }





    }

    fun submitExam(USERNAME: String){
        mydb.collection(USERNAME).document(quiz_name).set(qna)
        val newIntent = Intent(this, HomeActivity::class.java)
        startActivity(newIntent)
        this.finish()


        Log.d(TAG," examination is ended ${USERNAME} ")

    }

    fun addAnswer(question: String, answer: String){
        qna[question]= answer

    }
    fun EventChangeListener(){



        mydb.collection(quiz_name).addSnapshotListener(object: EventListener<QuerySnapshot> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type== DocumentChange.Type.ADDED){
                            Log.d(TAG, " this quiz exists : ${quiz_name}")
                            questionArrayList.add(dc.document.toObject(question_data::class.java))



                        }
                    }
                    adapter?.notifyDataSetChanged()
                }
            })



    }
}