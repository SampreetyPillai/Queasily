package com.example.queasily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login_button).setOnClickListener(){
            val newIntent = Intent(this, HomeActivity::class.java)
            newIntent.putExtra("USERNAME", findViewById<EditText>(R.id.mail_login).getText().toString())
            startActivity(newIntent)
        }
    }
}