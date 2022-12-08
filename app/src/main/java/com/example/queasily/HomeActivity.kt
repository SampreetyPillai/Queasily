package com.example.queasily

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import java.time.LocalDateTime

class HomeActivity : AppCompatActivity() {

    public var ongoing:String = "dashboard"

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myIntent = getIntent()
        val USERNAME:String? = myIntent.getStringExtra("USERNAME")

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.mytoolbar)

//        setSupportActionBar(toolbar)
//        val toggle:ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer)
//
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        val data = Bundle()
        data.putString("USERNAME", USERNAME)
        Log.d(TAG, "this is the username ${USERNAME}")
//        val mfrag:Fragment = dashboardFragment()
//        mfrag.arguments = data

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val mfrag:Fragment = dashboardFragment()
        mfrag.arguments = data
        fragmentTransaction.add(R.id.container, mfrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

       navigationView.setNavigationItemSelectedListener{item->


           val id:Int  = item.itemId

           when (id){
               R.id.upcoming_menu -> {
                   Toast.makeText(this, "Upcoming Quizes", Toast.LENGTH_SHORT).show()
                   val fragmentTransaction = supportFragmentManager.beginTransaction()
                   val mfrag:Fragment = upcomingFragment()
                   mfrag.arguments = data

                   fragmentTransaction.replace(R.id.container, mfrag)
                   fragmentTransaction.addToBackStack(null)
                   fragmentTransaction.commit()
                    }
               R.id.past_menu -> {
                   Toast.makeText(this, "Past quizes", Toast.LENGTH_SHORT).show()
                   val fragmentTransaction = supportFragmentManager.beginTransaction()
                   val mfrag:Fragment = dashboardFragment()
                   mfrag.arguments = data
                   fragmentTransaction.replace(R.id.container, mfrag)
                   fragmentTransaction.addToBackStack(null)
                   fragmentTransaction.commit()
                   }
               R.id.missed_menu -> {
                   Toast.makeText(this, "Missed Quizzes", Toast.LENGTH_SHORT).show()
                   val fragmentTransaction = supportFragmentManager.beginTransaction()
                   val mfrag:Fragment = dashboardFragment()
                   mfrag.arguments = data
                   fragmentTransaction.replace(R.id.container, mfrag)
                   fragmentTransaction.addToBackStack(null)
                   fragmentTransaction.commit()
                  }
               R.id.dashboard_menu -> {
                   Toast.makeText(this, "Your Dashboard", Toast.LENGTH_SHORT).show()
                   val fragmentTransaction = supportFragmentManager.beginTransaction()
                   val mfrag:Fragment = dashboardFragment()
                   mfrag.arguments = data
                   fragmentTransaction.replace(R.id.container, mfrag)
                   fragmentTransaction.addToBackStack(null)
                   fragmentTransaction.commit()
                   }

           }

          drawerLayout.closeDrawer(GravityCompat.START)
           true


       }




    }
    override fun onBackPressed() {
        val newfrag:Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (newfrag!=null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(newfrag)
            fragmentTransaction.commit()
        }
        super.onBackPressed()
    }


//    override fun OnNavigationItemSelected(item: MenuItem):Boolean{
//
//
//    }


}