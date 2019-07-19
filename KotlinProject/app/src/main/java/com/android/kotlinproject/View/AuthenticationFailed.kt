package com.android.kotlinproject.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.kotlinproject.AuthenticationFailedArgs
import com.android.kotlinproject.R

class AuthenticationFailed : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = AuthenticationFailedArgs.fromBundle(arguments!!)
        val email = args.email
        Toast.makeText(context,"Email  : $email ", Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication_failed, container, false)
    }


}
