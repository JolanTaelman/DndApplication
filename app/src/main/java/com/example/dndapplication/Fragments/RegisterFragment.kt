package com.example.dndapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dndapplication.R


class RegisterFragment : Fragment(), View.OnClickListener {

    private var listener: OnRegisterFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_register, container, false)
        val t = v.findViewById(R.id.RegisterSwapTextId) as TextView
        t.setOnClickListener(this)
        return v    }

    fun onButtonPressed() {
        listener?.switchLogin()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRegisterFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    override fun onClick(p0: View?) {
        listener?.switchLogin()
    }


    interface OnRegisterFragmentInteractionListener {
        fun switchLogin()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()

    }
}
