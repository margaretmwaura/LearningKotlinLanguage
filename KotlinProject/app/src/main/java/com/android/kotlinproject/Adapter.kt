package com.android.kotlinproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinproject.databinding.MonthItemBinding


//The variables that are being displayed are hsving the var keyWord because they are not being set on
//other variables for their values to be used
class Adapter : ListAdapter<Student,RecyclerView.ViewHolder>(StudentDiffCallBack())
{
//    var list = listOf<Student>()
//    set(value)
//    {
//        field=value
//        notifyDataSetChanged()
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding  = MonthItemBinding.inflate(layoutInflater,parent,false)

        return viewHolder(binding)
    }

//    override fun getItemCount(): Int
//    {
//      return list.size
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        var myStudent : Student = getItem(position)
        (holder as viewHolder).bind(myStudent)
    }


    class viewHolder(val binding: MonthItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var studentName : TextView =  binding.studentNameTv
        val studentMarks : TextView = binding.studentMarksTv
        fun bind(myStudent: Student)
        {
          binding.student = myStudent
            binding.executePendingBindings()
        }
    }

    class StudentDiffCallBack : DiffUtil.ItemCallback<Student>()
    {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean
        {
            return  oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem

        }

    }
}