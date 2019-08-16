package com.example.dndapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.example.dndapplication.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text


private const val ARG_PLAYER = "PLAYERNAME"

class HomeFragment : Fragment() {

    private var listener: OnHomeFragmentInteractionListener? = null
    private var playerName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerName = it.getString(ARG_PLAYER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val viewButton: View = v.findViewById(R.id.viewSheetButtonId) as Button
        val addButton: View = v.findViewById(R.id.AddSheetButtonId) as Button

        viewButton.setOnClickListener {
            onButtonPressed("view")
        }
        addButton.setOnClickListener {
            onButtonPressed("add")
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeWelcomeId.text = "Welcome $playerName"

    }

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
        fun newInstance(playerName: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAYER, playerName)
                }
            }
    }
}
