package com.android.kotlinproject


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.databinding.FragmentAuthenticationSuccessfulBinding



class AuthenticationSuccessful : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentAuthenticationSuccessfulBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_authentication_successful,container,false)

        registerNotificationChannel()

        var name : String = " "
        var marks : Int
        val myStudent : Student = Student()


//        Setting up the viewModel
        val application = requireNotNull(activity).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val studentViewModelFactoryInstance = StudentViewModelFactory(dataScource,application)

        val studentViewModelInstance = ViewModelProviders.of(activity as FragmentActivity,
            studentViewModelFactoryInstance).get(StudentViewModel::class.java)
        binding.myStudentViewModel = studentViewModelInstance
        binding.setLifecycleOwner(this)

        binding.buttonAddStudent.setOnClickListener {

            NewMessageNotification.notify(activity as FragmentActivity,"Entering", "Student data has beem entered", 1)
            name = binding.editTextStudentName.text.toString()
            marks = Integer.parseInt(binding.editTextStudentMarks.text.toString())

            myStudent.marks= marks
            myStudent.name = name
            studentViewModelInstance.onCreateNewStudent(myStudent)

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
        return binding.root
    }

    private fun registerNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val nm = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(NewMessageNotification.REMINDER_CHANENEL,"Enter data reminder" , NotificationManager.IMPORTANCE_DEFAULT)

            nm.createNotificationChannel(channel)
        }
    }


}
