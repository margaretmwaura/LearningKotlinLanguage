package com.android.kotlinproject.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.*
import com.android.kotlinproject.Model.Income
import com.android.kotlinproject.Model.student_database
import com.android.kotlinproject.Presenter.IncomeViewModel
import com.android.kotlinproject.Presenter.IncomeViewModelFactory
import com.android.kotlinproject.databinding.ActivityEnterIncomeBinding

class EnterIncome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityEnterIncomeBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_enter_income
        )

        var incomeName : String = " "
        var incomeDate : String = " "
        var incomeAmount : Int = 0

        val myIncome = Income()
        //        Setting up the viewModel
        val application = requireNotNull(this).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val incomeViewModelFactoryInstance =
            IncomeViewModelFactory(dataScource, application)

        val incomeViewModelInstance = ViewModelProviders.of(this,
            incomeViewModelFactoryInstance).get(IncomeViewModel::class.java)

        binding.myIncomeViewModel = incomeViewModelInstance
        binding.setLifecycleOwner(this)

        binding.button2.setOnClickListener {
            incomeName = binding.textViewIncomeName.text.toString()
            incomeDate = binding.textViewIncomeDate.text.toString()
            incomeAmount = Integer.parseInt(binding.textViewIncomeAmount.text.toString())

            myIncome.name = incomeName
            myIncome.amount = incomeAmount
            myIncome.incomeDate = incomeDate

            incomeViewModelInstance.onCreateNewIncome(myIncome)

        }

    }
}
