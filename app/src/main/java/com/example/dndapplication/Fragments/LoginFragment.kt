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
import com.google.android.gms.common.SignInButton
import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment(), OnClickListener {
    private var listener: OnLoginFragmentInteractionListener? = null


    /**
     * inflates the layout and sets the clickListeners.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.login_fragment, container, false)
        val b = v.findViewById(R.id.LoginButton) as Button
        val sb = v.find<SignInButton>(R.id.sign_in_button)

        b.setOnClickListener(this)
        sb.setOnClickListener(this)

        return v
    }

    /**
     * Triggers the click event on the listener depending on which button was clicked.
     */
    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.LoginButton -> {
                listener?.loginButtonClicked(AnonymousNameId.text.toString())

            }
            R.id.sign_in_button -> {
                listener?.signInClicked()
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
        fun loginButtonClicked(text: String)

        fun signInClicked()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
