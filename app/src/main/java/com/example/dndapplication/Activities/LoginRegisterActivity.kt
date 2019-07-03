package com.example.dndapplication.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.example.dndapplication.Fragments.LoginFragment
import com.example.dndapplication.Fragments.RegisterFragment
import com.example.dndapplication.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import kotlinx.android.synthetic.main.activity_login_register.*

class LoginRegisterActivity : AppCompatActivity(),  RegisterFragment.OnRegisterFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()

        showLoginFragment()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun showLoginFragment(){
        val fragment = LoginFragment.newInstance()
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_login, fragment)
            .commit()
    }

    private fun showRegisterFragment(){
        val fragment = RegisterFragment.newInstance()
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_login, fragment)
            .commit()
    }

    override fun loginButtonClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun switchLogin() {
        showLoginFragment()
    }

    override fun switchRegister() {
        showRegisterFragment()
    }

}
