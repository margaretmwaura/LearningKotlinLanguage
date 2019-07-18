package com.android.kotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.databinding.ActivityEnterExpenseBinding

class EnterExpense : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityEnterExpenseBinding = DataBindingUtil.setContentView(this,R.layout.activity_enter_expense)

        var expenseName : String = " "
        var expenseDate : String = " "
        var expenseDetail : String = " "
        var expenseAmount : Int = 0
        var myExpense = Expense()


        //        Setting up the viewModel
        val application = requireNotNull(this).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val expenseViewModelFactoryInstamce = ExpenseViewModelFactory(dataScource,application)

        val expenseViewModelInstance = ViewModelProviders.of(this,
            expenseViewModelFactoryInstamce).get(ExpenseViewModel::class.java)
        binding.myExpenseViewModel = expenseViewModelInstance
        binding.setLifecycleOwner(this)

        binding.buttonAddExpense.setOnClickListener {

            expenseName = binding.textViewExpenseName.text.toString()
            expenseDate = binding.textViewExpenseDate.text.toString()
            expenseDetail = binding.textViewExpenseDetail.text.toString()
            expenseAmount = Integer.parseInt(binding.textViewExpenseAmount.text.toString())

            myExpense.name = expenseName
            myExpense.expenseDate = expenseDate
            myExpense.details = expenseDetail
            myExpense.amount = expenseAmount

            expenseViewModelInstance.onCreateNewExpense(myExpense)


        }



    }
}
