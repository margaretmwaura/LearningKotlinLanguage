package com.android.kotlinproject.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinproject.databinding.ExpenseItemBinding

class ExpenseAdapter : ListAdapter<Expense, RecyclerView.ViewHolder>(ExpenseDiffCallBack())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding  = ExpenseItemBinding.inflate(layoutInflater,parent,false)

        return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        var myExpense : Expense = getItem(position)
        (holder as viewHolder).bind(myExpense)
    }

    class viewHolder(val binding: ExpenseItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var expenseName : TextView =  binding.expenseName
        val expenseTime : TextView = binding.expenseDate
        val expenseAmount : TextView = binding.expenseAmount
        val expenseDetails : TextView = binding.expenseDetails
        fun bind(myExpense: Expense)
        {
            binding.expense = myExpense
            binding.executePendingBindings()
        }
    }

    class ExpenseDiffCallBack : DiffUtil.ItemCallback<Expense>()
    {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean
        {
            return  oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean
        {
            return oldItem == newItem

        }

    }
}