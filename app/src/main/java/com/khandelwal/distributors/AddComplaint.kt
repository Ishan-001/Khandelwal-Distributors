package com.khandelwal.distributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment

class AddComplaint() : SupportBlurDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.add_complaint_layout, container, false)

        root.findViewById<TextView>(R.id.save).setOnClickListener {
            val request=Request(
                root.findViewById<EditText>(R.id.title).text.toString(),
                root.findViewById<EditText>(R.id.date).text.toString(),
                root.findViewById<EditText>(R.id.description).text.toString(),
                root.findViewById<EditText>(R.id.type).text.toString(),
                root.findViewById<EditText>(R.id.name).text.toString(),
                root.findViewById<EditText>(R.id.bill).text.toString(),
                false,
                0,
                "none"
            )

            val db= FirebaseDatabase.getInstance()
            val ref = db.getReference("requests").child("all")

            ref.push().setValue(request).addOnCompleteListener {
                dismiss()
            }
        }

        return root
    }
}