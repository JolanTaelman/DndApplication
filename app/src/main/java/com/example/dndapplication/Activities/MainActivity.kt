package com.example.dndapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.example.dndapplication.Fragments.HomeFragment
import com.example.dndapplication.Fragments.SheetAddFragment
import com.example.dndapplication.Fragments.SheetDetail
import com.example.dndapplication.R
import com.example.dndapplication.Fragments.SheetsFragment
import com.example.dndapplication.Models.DndClass
import com.example.dndapplication.Models.Sheet
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SheetsFragment.OnListFragmentInteractionListener, SheetAddFragment.OnClassListFragmentInteractionListener,
    HomeFragment.OnHomeFragmentInteractionListener {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var playerName: String

    /**
     * Homefragment Wisselen met andere fragments
     */
    override fun onHomeFragmentInteraction(page: String) {
        if (page === "view") {
            val fragment = SheetsFragment.newInstance(1)
            supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container_main, fragment)
                .addToBackStack(null)
                .commit()
        } else if (page === "add") {
            val fragment = SheetAddFragment.newInstance(playerName)
            supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container_main, fragment)
                .addToBackStack(null)
                .commit()
        }
    }


    /**
     * After saving a sheet, this method is called to replace the add fragment with the home fragment
     */
    override fun returnHome() {
        val fragment = HomeFragment.newInstance(playerName)
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_main, fragment)
            .commit()
    }

    /**
     * On interaction with the dnd class, set the selected class in the add fragment to the selected class.
     */
    override fun onListFragmentInteraction(item: DndClass?) {
        val fragManager = this.supportFragmentManager
        val frag = fragManager.fragments[1]
        (frag as SheetAddFragment).setDndClass(item!!)
    }

    /**
     * On interaction with a sheet from the sheetlist fragment, open the sheetDetail fragment with the selected sheet provided.
     */
    override fun onListFragmentInteraction(item: Sheet?) {
        val fragment = SheetDetail.newInstance(item!!)
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * sets the layouts and starts the home fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Build the google signinOptions, needed to starts a session and log out hte user.
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()

        playerName =  intent.getStringExtra("PlayerName")
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        navView.setNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {

            val fragment = HomeFragment.newInstance(playerName)
            supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container_main, fragment)
                .commit()
        }
    }

    /**
     * Overrides the back button to close the drawer if it is opened
     */
    override fun onBackPressed() {

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * Signs out the user if they are logged in and returns to the login screen.
     */
    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                val intent = Intent(this, LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
    }

    /**
     * Handles navigation from the navigation drawer, matching hte click with the correct fragment navigation and closes the drawer.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_add -> {
                val fragment = SheetsFragment.newInstance(1)
                supportFragmentManager
                    .beginTransaction().replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_list -> {
                val fragment = SheetAddFragment.newInstance(playerName)
                supportFragmentManager
                    .beginTransaction().replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            R.id.nav_logout -> {
                signOut()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
