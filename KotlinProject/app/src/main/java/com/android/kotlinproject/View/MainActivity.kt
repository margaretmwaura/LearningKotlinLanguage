package com.android.kotlinproject.View
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.Model.Adapter
import com.android.kotlinproject.R
import com.android.kotlinproject.Presenter.StudentViewModel
import com.android.kotlinproject.Presenter.StudentViewModelFactory
import com.android.kotlinproject.databinding.ActivityMainBinding
import com.android.kotlinproject.Model.student_database
import timber.log.Timber

class MainActivity : AppCompatActivity()
{



    override fun onCreate(savedInstanceState: Bundle?)
    {
            super.onCreate(savedInstanceState)
      val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
          R.layout.activity_main
      )

        val application = requireNotNull(this).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val studentViewModelFactoryInstance =
            StudentViewModelFactory(dataScource, application)

        val studentViewModelInstance = ViewModelProviders.of(this,studentViewModelFactoryInstance).get(StudentViewModel::class.java)
        binding.myStudentViewModel = studentViewModelInstance
        val adapater = Adapter()
        binding.monthsOfTheYear.adapter = adapater
        studentViewModelInstance.studentList.observe(this , Observer {
            it?.let {
                adapater.submitList(it)
                Timber.d("This is the size of the list : ${it.size}")
            }
        })


        binding.setLifecycleOwner(this)


    }

//    This is for generating the months



}
