package com.android.kotlinproject.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.kotlinproject.Presenter.fragmentToActivity
import com.android.kotlinproject.R
import com.android.kotlinproject.databinding.ActivityWelcomBinding

class Welcom : AppCompatActivity() , fragmentToActivity {


    companion object
    {
        public var email : String = " "
        fun emailGiven(): String
        {
            return email
        }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding : ActivityWelcomBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_welcom
        )
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
       return navController.navigateUp()
    }

    override fun communicate(comm: String)
    {
       email = comm
    }
}
