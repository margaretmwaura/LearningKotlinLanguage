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
@BindingAdapter("GivenExpenseName")
fun TextView.setExpenseName(item : Expense?)
{
    item?.let {
        text = item.name
    }
}
@BindingAdapter("GivenExpenseAmount")
fun TextView.setExpenseAmount(item : Expense?)
{
    item?.let {
        text = item.amount.toString()
    }
}
@BindingAdapter("GivenExpenseDate")
fun TextView.setExpenseDate(item : Expense?)
{
    item?.let {
        text = item.expenseDate
    }
}
@BindingAdapter("GivenStudentMarks")
fun TextView.setExpenseDetails(item : Expense?)
{
    item?.let {
        text = item.details
    }
}
@BindingAdapter("GivenIcomeName")
fun TextView.setIncomeName(item : Income?)
{
    item?.let {
        text = item.name
    }
}
@BindingAdapter("GivenIncomeAmount")
fun TextView.setIncomeAmount(item : Income?)
{
    item?.let {
        text = item.amount.toString()
    }
}
@BindingAdapter("GivenIncomeDate")
fun TextView.setIncomeDate(item : Income?)
{
    item?.let {
        text = item.incomeDate
    }
}
