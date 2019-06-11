package com.example.dicerollernew

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*
import com.example.dicerollernew.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val myName : MyName = MyName("Maggie")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
       binding.myName = myName
        binding.buttonRole.text= getString(R.string.lets_role)

        binding.buttonRole.setOnClickListener {
            Toast.makeText(this,"Kotlin", Toast.LENGTH_LONG).show()
            rollDice()
        }
    }

    private fun rollDice()
    {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        var randomInt = Random().nextInt(3)+1
        val drawableResource = when(randomInt)
        {
            1 -> R.drawable.ic_launcher_background
            2 -> R.drawable.abc_ab_share_pack_mtrl_alpha
            else -> R.drawable.ic_launcher_foreground

        }
        binding.diceImage.setImageResource(drawableResource)

    }
}
