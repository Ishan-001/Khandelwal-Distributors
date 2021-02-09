package com.khandelwal.distributors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ViewComplaint : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.complaint_view_layout, container, false)
        return root
    }
}