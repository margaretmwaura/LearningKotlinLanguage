package com.android.kotlinproject
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
      val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val application = requireNotNull(this@MainActivity).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val studentViewModelFactoryInstance = StudentViewModelFactory(dataScource,application)

        val studentViewModelInstance = ViewModelProviders.of(this@MainActivity as FragmentActivity,
            studentViewModelFactoryInstance).get(StudentViewModel::class.java)
        binding.myStudentViewModel = studentViewModelInstance
        val adapater = Adapter()
        binding.monthsOfTheYear.adapter = adapater
        studentViewModelInstance.studentList.observe(this , Observer {
            it?.let {
                adapater.submitList(it)
            }
        })


        binding.setLifecycleOwner(this)


    }

//    This is for generating the months



}
