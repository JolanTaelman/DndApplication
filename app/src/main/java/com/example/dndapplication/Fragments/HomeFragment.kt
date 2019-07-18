package com.example.dndapplication.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.dndapplication.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var listener: OnHomeFragmentInteractionListener? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)
        var viewButton: View = v.findViewById(R.id.viewSheetButtonId) as Button
        var addButton: View = v.findViewById(R.id.AddSheetButtonId) as Button

        viewButton.setOnClickListener {
            onButtonPressed("view")
        }
        addButton.setOnClickListener {
            onButtonPressed("add")
        }

        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(page: String) {
        listener?.onHomeFragmentInteraction(page)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    interface OnHomeFragmentInteractionListener {
        fun onHomeFragmentInteraction(page: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
