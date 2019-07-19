package com.android.kotlinproject.View

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.android.kotlinproject.*
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*

class drawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var email : String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        Toast.makeText(this,"This is the email : ${Welcom.emailGiven()}" , Toast.LENGTH_LONG).show()
        email = Welcom.emailGiven()


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val h_view = nav_view.getHeaderView(0)
        val textView = h_view.findViewById(R.id.textView_email_user) as TextView
        textView.text = email
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera ->
            {
                // Handle the camera action
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
                val expenseFragment = ExpenseFragment()
                val fragmenttransaction = supportFragmentManager.beginTransaction()
                fragmenttransaction.replace(R.id.container_layout,expenseFragment)
                fragmenttransaction.commit()
            }
            R.id.nav_gallery ->
            {
                val incomeFragment = IncomeFragment()
                val fragmenttransaction = supportFragmentManager.beginTransaction()
                fragmenttransaction.replace(R.id.container_layout,incomeFragment)
                fragmenttransaction.commit()
            }
            R.id.nav_slideshow ->
            {
                val intent = Intent(this@drawerActivity, MainActivity::class.java)
                startActivity(intent)

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
