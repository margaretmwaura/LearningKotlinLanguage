package com.android.kotlinproject.View


import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.kotlinproject.AuthenticationFragmentDirections
import com.android.kotlinproject.R
import com.android.kotlinproject.databinding.FragmentAuthenticationBinding
import com.android.kotlinproject.Presenter.fragmentToActivity
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber


class AuthenticationFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mcallBack : fragmentToActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        Timber.d("Authentication started")
        auth = FirebaseAuth.getInstance()
        var email : String = " "
        var password : String = " "

        // Inflate the layout for this fragment
        val binding : FragmentAuthenticationBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_authentication,container,false)

        binding.button.setOnClickListener {
            email = binding.editTextEmail.text.toString()
            password = binding.editTextPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity as Activity) {task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.e("Creating a user was successfule")
                        val user = auth.currentUser
                        sendData(email)
                        it.findNavController().navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToAuthenticationSuccessful())


                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.e("Error creating a user")
                        it.findNavController().navigate(
                            AuthenticationFragmentDirections.actionAuthenticationFragmentToAuthenticationFailed(
                                email
                            )
                        )
                    }

                    }


                }


        return binding.root

        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mcallBack = context as fragmentToActivity
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement FragmentToActivity")
        }

    }

    fun sendData(email : String)
    {
        mcallBack.communicate(email)
    }

    }



