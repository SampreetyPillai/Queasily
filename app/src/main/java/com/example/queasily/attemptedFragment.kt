package com.example.queasily

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [attemptedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class attemptedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var layoutManager: RecyclerView.LayoutManager?= null
    private lateinit var quizArrayList_past: ArrayList<quiz_data>
    private var adapter: RecyclerView.Adapter<quizAdapter_past.ViewHolder>? = null
    var my_username:String?=null

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
        val USERNAME = arguments?.getString(dashboardFragment.ARG_NAME)
        my_username = USERNAME
        val view:View = inflater.inflate(R.layout.fragment_attempted, container, false)
//        view.findViewById<TextView>(R.id.sample_upcoming).text = "This is the username " +USERNAME.toString()


        quizArrayList_past = arrayListOf()

        layoutManager = LinearLayoutManager(requireContext())

        val rec: RecyclerView = view.findViewById(R.id.qattempted)

        rec.layoutManager = layoutManager

        adapter = quizAdapter_past(quizArrayList_past,upcomingFragment())

        rec.adapter = adapter



        EventChangeListener()
        return view
    }

    fun EventChangeListener(){
        val mydb = Firebase.firestore
        mydb.collection("quiz_details")
            .addSnapshotListener(object: EventListener<QuerySnapshot> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type== DocumentChange.Type.ADDED){

                            if(status[dc.document.toObject(quiz_data::class.java).quizname]=="attempted"){
                                quizArrayList_past.add(dc.document.toObject(quiz_data::class.java))
                            }

                        }
                    }
                    adapter?.notifyDataSetChanged()
                }
            })
    }

    companion object {
        const val ARG_NAME_A = "USERNAME"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment attemptedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param0:String,param1: String, param2: String) =
            attemptedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_NAME_A, param0)
                }
            }
    }
}