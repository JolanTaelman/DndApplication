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
    //options needed to make use of the google login feature.
    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        showLoginFragment()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    /**
     * If the user is still logged in through google, navigate towards the main activity
     */
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

    /**
     * starts the google signing activity
     */
    private fun signIn() {
       val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /**
     * Adds the login Fragment to the activity
     */
    private fun showLoginFragment(){
        val fragment = LoginFragment.newInstance()
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_login, fragment)
            .commit()
    }

    /**
     * Takes the username passed and goes to the main activity.
     */
    override fun loginButtonClicked(text: String) {
        goToMain(text)
    }

    /**
     * starts the Main activity with the provided player name
     */
    private fun goToMain(text: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("PlayerName", text)
        startActivity(intent)
        finish()
    }

    /**
     * Get's the result from the google login activity and passes this along.
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    /**
     * If succesfully logged in, continue on to the main activity with the google display name of the logged in user.
     */
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
