package com.example.queasily

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController

class quizAdapter_missed(private val qlist:ArrayList<quiz_data>, private val context: missedFragment): RecyclerView.Adapter<quizAdapter_missed.ViewHolder>() {


    private lateinit var cont: Context
    private lateinit var rootView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): quizAdapter_missed.ViewHolder{

        val v = LayoutInflater.from(parent.context).inflate(R.layout.quiz_layout, parent, false)

        cont = parent.getContext()
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return qlist.size
    }

    override fun onBindViewHolder(holder:quizAdapter_missed.ViewHolder, position: Int){


        val curr: quiz_data = qlist[position]
        holder.quiz_name.text = curr.quizname
        holder.quiz_publish.text = curr.quiz_publish
        holder.teacher_name.text = curr.teacher_name
        holder.mybutton.text = "Go To Quiz"

        holder.mybutton.setOnClickListener{
            val bundle = Bundle()
            // Context = this.context


            if (AccessController.getContext() !=null){
                val newIntent = Intent(cont, Questions::class.java)
                newIntent.putExtra("QUIZNAME", curr.quizname)
                newIntent.putExtra("QUIZTEACHER", curr.teacher_name)
                newIntent.putExtra("QUIZPUBLISH", curr.quiz_publish)
                newIntent.putExtra("QUIZEND", curr.quiz_end)
                newIntent.putExtra("QUIZDURATION", curr.quiz_duration)
                newIntent.putExtra("QUIZNAME", curr.quizname)
                newIntent.putExtra("USERNAME", context.my_username)
                ContextCompat.startActivity(cont, newIntent, bundle)
            }


        }

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        var quiz_name: TextView = itemView.findViewById(R.id.qname)
        var quiz_publish: TextView = itemView.findViewById(R.id.date_publish)
        var teacher_name: TextView = itemView.findViewById(R.id.teacher)
        var mybutton: Button = itemView.findViewById(R.id.attempt)


//        fun ViewHolder(itemView: View) {
//            mybutton.setOnClickListener() {
//                val newIntent = Intent(itemView.context, PageActivity::class.java)
//                itemView.context.startActivity(newIntent)
//            }
//
//
//        }


    }
}