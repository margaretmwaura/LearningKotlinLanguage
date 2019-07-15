package com.android.kotlinproject

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("GivenStudentName")
fun TextView.setStudentName(item : Student?)
{
    item?.let {
        text = item.name
    }
}

@BindingAdapter("GivenStudentMarks")
fun TextView.setStudentMarks(item : Student?)
{
    item?.let {
        text = item.marks.toString()
    }
}