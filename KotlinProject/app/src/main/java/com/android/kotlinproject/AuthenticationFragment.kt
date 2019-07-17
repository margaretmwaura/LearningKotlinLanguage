package com.android.kotlinproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.kotlinproject.databinding.FragmentAuthenticationBinding


class AuthenticationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentAuthenticationBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_authentication,container,false)
        binding.authSuccess.setOnClickListener {
            it.findNavController().navigate(R.id.action_authenticationFragment_to_authenticationSuccessful)
        }
        binding.authFailed.setOnClickListener {
            it.findNavController().navigate(R.id.action_authenticationFragment_to_authenticationFailed)
        }
        // Inflate the layout for this fragment
        return binding.root

    }


}
