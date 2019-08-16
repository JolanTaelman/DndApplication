package com.example.dndapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dndapplication.Fragments.LoginFragment
import com.example.dndapplication.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginRegisterActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentInteractionListener{

    private val RC_SIGN_IN = 9001
    lateinit var mGoogleSignInClient: GoogleSignInClient

    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        showLoginFragment()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

   override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
       if (account != null) {
           goToMain(account.displayName.toString())
       }
   }

    override fun signInClicked() {
        signIn()
    }

    private fun signIn() {
       val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun showLoginFragment(){
        val fragment = LoginFragment.newInstance()
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_login, fragment)
            .commit()
    }

    override fun loginButtonClicked(text: String) {
        goToMain(text)
    }

    private fun goToMain(text: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("PlayerName", text)
        startActivity(intent)
        finish()
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully
            goToMain(account?.displayName.toString())
        } catch (e: ApiException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }



}
