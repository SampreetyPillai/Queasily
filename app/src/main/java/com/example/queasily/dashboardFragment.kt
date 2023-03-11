package com.example.queasily

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.ResourceBundle.getBundle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [dashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class dashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//

        val USERNAME = arguments?.getString(ARG_NAME)
//        if (USERNAME != null) {
//            Username = USERNAME
//        }

        var view:View = inflater.inflate(R.layout.fragment_dashboard, container, false)

       // getAtt()
       // Log.d(TAG, " ${Username} and ${mycontact}")
        Log.d(TAG, " THE_USERNAME ${THE_USERNAME}")

        view.findViewById<TextView>(R.id.your_email).text = "USERNAME: " +THE_USERNAME.toString()
        view.findViewById<TextView>(R.id.your_contact).text ="CONTACT: "+ MYCONTACT.toString()
        view.findViewById<TextView>(R.id.your_name).text = "NAME: "+THE_NAME.toString()
        val changepass:EditText = view.findViewById(R.id.changepass)
        val changepassconfirm:EditText = view.findViewById(R.id.changepassconfirm)
        view.findViewById<Button>(R.id.button2).setOnClickListener(){
            changepass.visibility = View.VISIBLE
            changepass.hint = "Enter new password"

            changepassconfirm.visibility = View.VISIBLE
            changepassconfirm.hint = "Confirm password here"

            view.findViewById<Button>(R.id.passchangesubmit).visibility = View.VISIBLE
        }
        view.findViewById<Button>(R.id.passchangesubmit).setOnClickListener(){
            val currentpass: String = changepass.text.toString()
            val confirmpass:String = changepass.text.toString()
            if(currentpass!=confirmpass){
                Toast.makeText(context,"Password not confirmed", Toast.LENGTH_SHORT).show()
            }else{
                var k:Int = 0

                val db  = Firebase.firestore
                db.collection("users").get().addOnSuccessListener { result->
                    for(document in result){
                        if (document.data.get("USERNAME")==THE_USERNAME){
                            val id:String = document.id
                            db.collection("users").document(id).update("PASSWORD",currentpass)
                            k=1
                            Toast.makeText(context,"Password changed", Toast.LENGTH_SHORT).show()
                            break

                        }
                    }

                }

            }
        }

        return view
    }

//    fun getAtt(){
//
//        val db = Firebase.firestore
//
//
//
//
//
//
//    }

    companion object {
        const val ARG_NAME = "USERNAME"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment dashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param0:String,param1: String, param2: String,param3:String) =
            dashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_NAME, param0)

                }

            }
    }
}