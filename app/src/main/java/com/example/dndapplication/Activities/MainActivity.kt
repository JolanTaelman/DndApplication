package com.example.dndapplication.Activities

import android.net.Uri
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.example.dndapplication.R
import com.example.dndapplication.Fragments.SheetFragment
import com.example.dndapplication.Fragments.SheetsFragment
import com.example.dndapplication.Models.Sheet
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SheetFragment.OnFragmentInteractionListener, SheetsFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Sheet?) {
        val fragment = SheetFragment.newInstance("param1", "param2")
        supportFragmentManager
            .beginTransaction().replace(R.id.fragment_container_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


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

        navView.setNavigationItemSelectedListener(this)
    }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = SheetsFragment.newInstance(1)
                supportFragmentManager
                    .beginTransaction().replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_gallery -> {
                val fragment = SheetFragment.newInstance("param1", "param2")
                supportFragmentManager
                    .beginTransaction().replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_slideshow -> {
                val fragment = SheetsFragment.newInstance(2)
                supportFragmentManager
                    .beginTransaction().replace(R.id.fragment_container_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
