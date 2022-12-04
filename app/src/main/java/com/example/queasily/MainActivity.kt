package com.example.queasily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.login_button_main).setOnClickListener{
            val newIntent = Intent(this, LoginActivity::class.java)
            startActivity(newIntent)
        }

        findViewById<Button>(R.id.register_button_main).setOnClickListener{
            val newIntent = Intent(this, RegisterActivity::class.java)
            startActivity(newIntent)
        }
    }
}