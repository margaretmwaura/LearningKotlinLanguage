package com.android.kotlinproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.android.kotlinproject.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding : FragmentWelcomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false)
        // Inflate the layout for this fragment
        binding.startAuthentication.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_authenticationFragment)
//            Changed to
//            it.findNavController().navigate(pass the id )
//            changes to

        }
        return binding.root
    }


}
