package com.example.queasily

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


public var status:MutableMap<String,String> = mutableMapOf()
public var THE_USERNAME: String = ""
public var MYCONTACT: String = ""
public var THE_NAME: String = ""


class LoginActivity : AppCompatActivity() {
    private val fAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login_button).setOnClickListener(){


            val db = Firebase.firestore
            val MAIL:EditText = findViewById(R.id.mail_login)
            val PASSWORD:EditText = findViewById(R.id.pass_login)



            db.collection("users").get()
                .addOnSuccessListener { result ->
                    for (document in result) {

                        Log.d(TAG,"inside loop")
                        val name = document.data.get("USERNAME")
                        THE_USERNAME = name.toString()
                        MYCONTACT = document.data.get("CONTACT").toString()
                        THE_NAME = document.data.get("NAME").toString()

                        val password = document.data.get("PASSWORD")
                        //Log.d(TAG, "${name} => ${usern.getText().toString()}")
                        if (name == MAIL.getText().toString() && password == PASSWORD.getText().toString()) {
                            Log.d(TAG, "inside the statement")
                            val verified = fAuth.currentUser?.isEmailVerified

                            fAuth.currentUser?.reload()
                            if(verified==true)
                            {
                                Log.d(TAG, "email is verified")
                                val newIntent = Intent(this, HomeActivity::class.java)
                                newIntent.putExtra("USERNAME", MAIL.getText().toString())
                                startActivity(newIntent)
                                finish()

//                                db.collection(MAIL.getText().toString()).get().addOnSuccessListener{
//                                    givenQuiz->
//
//                                    for(thisq in givenQuiz){
//                                        status[thisq.id] = "attempted"
//
//                                    }
//                                }


                                Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show()
                            }

                            break

                        }

                    }
                }

        }
    }
}