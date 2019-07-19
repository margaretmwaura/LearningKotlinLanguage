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
import com.android.kotlinproject.databinding.FragmentIncomeBinding
import timber.log.Timber

class IncomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding : FragmentIncomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_income,container,false)
        val application = requireNotNull(activity).application
        val dataScource = student_database.getInstance(application).dao_interface_interface

        val incomeViewModelFactoryInstance = IncomeViewModelFactory(dataScource,application)
        val incomeViewModelInstance = ViewModelProviders.of(this,incomeViewModelFactoryInstance).get(IncomeViewModel::class.java)
        binding.myIncomeViewModel = incomeViewModelInstance
        val incomeadapter = IncomeAdapter()
        binding.recyclerviewIncome.adapter = incomeadapter

        incomeViewModelInstance.incomeList.observe(this, Observer {

            incomeadapter.submitList(it)
            Timber.d("This is the size of the list : ${it.size}")
        })

        binding.fabAddIncome.setOnClickListener {

            val intent = Intent(context,EnterIncome::class.java)
            startActivity(intent)
        }


       return binding.root
    }


}
