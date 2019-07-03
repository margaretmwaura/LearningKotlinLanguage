package com.jwhh.notekeeper

import android.content.Context
import android.graphics.Color
import android.support.annotation.StyleRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.color_selector.view.*

class ColorSelector @JvmOverloads

    constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

        private var listOfColors = listOf(Color.BLUE, Color.RED, Color.GREEN)
        private var selectedColorIndex = 0
        init {
            orientation = LinearLayout.HORIZONTAL
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.color_selector, this)
            selected_color.setBackgroundColor(listOfColors[selectedColorIndex])

            backLeftArrow.setOnClickListener()
            {
                selectPreviousColor()
            }
            backRight.setOnClickListener()
            {
                selectColorNext()
            }

            enable_color.setOnCheckedChangeListener { buttonView, isChecked ->   broadCastColor()  }


        }

    var colorselectinterface : colorSelectInterface? = null
    interface colorSelectInterface
    {
        fun onColorSelected(color : Int)
    }

    fun setColorSelectListener(listener : colorSelectInterface)
    {
        this.colorselectinterface = listener
    }

    fun setelectedColor(color : Int)
    {
        var index = listOfColors.indexOf(color)
        if(index == -1)
        {
            enable_color.isChecked = false
            index = 0
        }
        else
        {
            enable_color.isChecked = true
        }

        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
    }

    private fun broadCastColor()
    {
        val color = if(enable_color.isChecked)

            listOfColors[selectedColorIndex]

        else

            Color.TRANSPARENT

        this.colorselectinterface?.onColorSelected(color)
    }

    private fun selectPreviousColor() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = listOfColors.lastIndex
        } else {
            selectedColorIndex--
        }

        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
        broadCastColor()
    }

    private fun selectColorNext() {

        if (selectedColorIndex == listOfColors.lastIndex) {
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }

        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
        broadCastColor() }

}


