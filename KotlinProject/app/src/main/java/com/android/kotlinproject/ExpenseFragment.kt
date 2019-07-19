package com.android.kotlinproject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.kotlinproject.databinding.FragmentExpenseBinding
import timber.log.Timber

class ExpenseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentExpenseBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_expense,container,false)
        val application = requireNotNull(activity).application
        val dataScource = student_database.getInstance(application).dao_interface_interface
        val expenseViewModelFactoryInstance = ExpenseViewModelFactory(dataScource,application)

        val expenseViewModel = ViewModelProviders.of(this,expenseViewModelFactoryInstance).get(ExpenseViewModel::class.java)
        binding.myExpenseViewModel = expenseViewModel
        val expenseAdapter = ExpenseAdapter()
        binding.recyclerviewExpense.adapter = expenseAdapter

        expenseViewModel.expenseList.observe(this, Observer {

            expenseAdapter.submitList(it)
            Timber.d("This is the size of the list : ${it.size}")
        })


        binding.fab.setOnClickListener {

            val intent = Intent(context,EnterExpense::class.java)
            startActivity(intent)
        }
        return binding.root
    }


}
