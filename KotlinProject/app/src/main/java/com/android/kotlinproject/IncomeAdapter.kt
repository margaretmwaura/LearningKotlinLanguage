package com.android.kotlinproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinproject.databinding.IncomeItemBinding


class IncomeAdapter : ListAdapter<Income,RecyclerView.ViewHolder>(IncomeDiffCallBack())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
       var layoutInflater = LayoutInflater.from(parent.context)
        val binding = IncomeItemBinding.inflate(layoutInflater,parent,false)
        return IncomeAdapter.viewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
       var myIncome : Income = getItem(position)
        (holder as IncomeAdapter.viewHolder).bind(myIncome)
    }

    class viewHolder(val binding: IncomeItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var incomeName : TextView =  binding.incomeName
        val incomeAmount : TextView = binding.incomeAmount
        val incomeDate : TextView = binding.incomeDate

        fun bind(myIncome : Income)
        {
            binding.income = myIncome
            binding.executePendingBindings()
        }
    }
    class IncomeDiffCallBack : DiffUtil.ItemCallback<Income>()
    {
        override fun areItemsTheSame(oldItem: Income, newItem: Income): Boolean
        {
            return  oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Income, newItem: Income): Boolean
        {
            return oldItem == newItem

        }

    }
}