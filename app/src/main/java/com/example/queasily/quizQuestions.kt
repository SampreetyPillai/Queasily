package com.example.queasily

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController




class quizQuestions(private val questionlist:ArrayList<question_data>, private val activity: Questions): RecyclerView.Adapter<quizQuestions.ViewHolder>()  {

    private lateinit var cont: Context
    private lateinit var rootView: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): quizQuestions.ViewHolder{

        val v = LayoutInflater.from(parent.context).inflate(R.layout.quiz_questions, parent, false)

        cont = parent.getContext()
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return questionlist.size
    }

    override fun onBindViewHolder(holder:quizQuestions.ViewHolder, position: Int){


        val curr: question_data = questionlist[position]
        holder.q_no.text = curr.q_no
        val q_no:String? = curr.q_no
        holder.question_desc.text = curr.question_desc
        holder.option1.text = curr.option1
        holder.option2.text = curr.option2
        holder.option3.text = curr.option3
        holder.option4.text = curr.option4
        holder.option1.setOnClickListener(){

            if (q_no != null) {
                this.activity.addAnswer(q_no, "1")
            }
        }

        holder.option2.setOnClickListener(){

            if (q_no != null) {
                this.activity.addAnswer(q_no, "2")
            }
        }
        holder.option3.setOnClickListener(){

            if (q_no != null) {
                this.activity.addAnswer(q_no, "3")
            }
        }
        holder.option4.setOnClickListener(){

            if (q_no != null) {
                this.activity.addAnswer(q_no, "4")
            }
        }




    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        var q_no: TextView = itemView.findViewById(R.id.q_no)
        var question_desc: TextView = itemView.findViewById(R.id.question_desc)
        var option1: RadioButton = itemView.findViewById(R.id.option1)
        var option2: RadioButton = itemView.findViewById(R.id.option2)
        var option3: RadioButton = itemView.findViewById(R.id.option3)
        var option4: RadioButton = itemView.findViewById(R.id.option4)
        var theoptions: RadioGroup = itemView.findViewById(R.id.radio_options)
       // var option1c: CheckBox = itemView.findViewById(R.id.option1c)




    }
}