package com.android.kotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.kotlinproject.databinding.ActivityAuthenticationBinding

import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class Authentication : AppCompatActivity()
{

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityAuthenticationBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)

        Timber.d("Authentication started")
        auth = FirebaseAuth.getInstance()

        var email : String = " "
        var password : String = " "
        binding.buttonAuthenticate.setOnClickListener {
            email = binding.textViewEmail.text.toString()
            password = binding.textViewPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                      Timber.e("Creating a user was successfule")
                        val user = auth.currentUser

                        val intent = Intent(this@Authentication, EnterStudentData::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                     Timber.e("Error creating a user")
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }


                }


        }
    }
}
