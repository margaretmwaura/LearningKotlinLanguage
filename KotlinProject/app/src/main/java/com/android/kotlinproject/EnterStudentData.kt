package com.android.kotlinproject


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.databinding.ActivityEnterStudentDataBinding


import kotlinx.android.synthetic.main.activity_enter_student_data.*

class EnterStudentData : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Now setting up the data binding
        val binding : ActivityEnterStudentDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_enter_student_data)

        var name : String = " "
        var marks : Int
        val myStudent : Student = Student()


//        Setting up the viewModel
        val application = requireNotNull(this@EnterStudentData).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val studentViewModelFactoryInstance = StudentViewModelFactory(dataScource,application)

        val studentViewModelInstance = ViewModelProviders.of(this@EnterStudentData as FragmentActivity,
            studentViewModelFactoryInstance).get(StudentViewModel::class.java)
        binding.myStudentViewModel = studentViewModelInstance
        binding.setLifecycleOwner(this)

        binding.enterStudentData.setOnClickListener {

            name = student_name.text.toString()
            marks = Integer.parseInt(student_marks.text.toString())

            myStudent.marks= marks
            myStudent.name = name
            studentViewModelInstance.onCreateNewStudent(myStudent)

            val intent = Intent(this@EnterStudentData, MainActivity::class.java)
            startActivity(intent)

        }

    }
}
