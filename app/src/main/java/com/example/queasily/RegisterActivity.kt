package com.example.queasily

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private val fAuth = Firebase.auth

    private lateinit var  PASSWORD :EditText
    private lateinit var CONF_PASSWORD : EditText
    private lateinit var CONTACT :EditText
    private lateinit var  MAIL :EditText
    private lateinit var NAME :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        PASSWORD = findViewById(R.id.register_pass)
        CONF_PASSWORD = findViewById<EditText>(R.id.register_con_pass)
        CONTACT = findViewById<EditText>(R.id.register_contact)
        MAIL = findViewById<EditText>(R.id.register_mail)
        NAME = findViewById<EditText>(R.id.register_name)

        findViewById<Button>(R.id.register_button).setOnClickListener {
            validateEmptyForm()


        }
    }


        fun fireBaseSignUp() {
            fAuth.createUserWithEmailAndPassword(MAIL.text.toString(), PASSWORD.text.toString())
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {

                        Log.d(TAG, "registration completed")

                        fAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT)
                                .show()
                        }


                        val users = hashMapOf(
                            "USERNAME" to MAIL.getText().toString(),
                            "PASSWORD" to PASSWORD.getText().toString(),
                            "CONTACT" to CONTACT.getText().toString(),
                            "NAME" to NAME.getText().toString()
                        )
                        db.collection("users").add(users)
                        val USERNAME:String = MAIL.getText().toString()
                        db.collection(USERNAME).add(hashMapOf("0" to "A"))
                        val verified = fAuth.currentUser?.isEmailVerified
                        if (verified == true) {
                            val newIntent = Intent(this, HomeActivity::class.java)
                            startActivity(newIntent)

                        } else {
                            Toast.makeText(this, "Verify your mail", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }

        }

        fun validateEmptyForm() {
            var icon = AppCompatResources.getDrawable(this, R.drawable.error)
            icon?.setBounds(0, 0, 100, 100)
            when {
                TextUtils.isEmpty(MAIL.toString().trim()) -> {
                    MAIL.setError("Please enter username", icon)
                }
                TextUtils.isEmpty(PASSWORD.text.toString().trim()) -> {
                    PASSWORD.setError("Please enter password", icon)
                }
                TextUtils.isEmpty(CONF_PASSWORD.text.toString().trim()) -> {
                    CONF_PASSWORD.setError("Please re-enter password", icon)
                }


                MAIL.text.toString().isNotEmpty() &&
                        PASSWORD.text.toString().isNotEmpty() &&
                        CONF_PASSWORD.text.toString().isNotEmpty() -> {
                    //[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+
                    if (MAIL.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                        if (PASSWORD.text.toString().length >= 5) {
                            if (PASSWORD.text.toString() == CONF_PASSWORD.text.toString()) {
                                fireBaseSignUp()
                                // Toast.makeText(context, "Registration Successful",Toast.LENGTH_SHORT).show()
                            } else {
                                CONF_PASSWORD.setError("Please re-enter valid password", icon)
                            }
                        } else {
                            PASSWORD.setError("Please enter valid password", icon)
                        }
                    } else {
                        MAIL.setError("Please enter valid email id", icon)
                    }
                }
            }
        }


}

