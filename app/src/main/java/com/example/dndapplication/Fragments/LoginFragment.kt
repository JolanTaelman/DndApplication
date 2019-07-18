package com.example.dndapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import com.example.dndapplication.R
import android.widget.TextView

class LoginFragment : Fragment(), OnClickListener {
    private var listener: OnLoginFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.login_fragment, container, false)
        val t = v.findViewById(R.id.LoginSwapTextId) as TextView
        val b = v.findViewById(R.id.LoginButton) as Button
        t.setOnClickListener(this)
        b.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.LoginSwapTextId -> {
                listener?.switchRegister()
            }
            R.id.LoginButton -> {
                listener?.loginButtonClicked()
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnLoginFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnLoginFragmentInteractionListener {
        fun switchRegister()

        fun loginButtonClicked()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
