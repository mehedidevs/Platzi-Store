package com.mehedi.platzistore.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.mehedi.platzistore.R
import com.mehedi.platzistore.databinding.FragmentLoginBinding
import com.mehedi.platzistore.model.data.login.RequestLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    private val viewmodel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {

            val email = binding.userEmail?.text.toString()
            val password = binding.password.text.toString()

            handleLogin(email, password)

        }


        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)



        }



        viewmodel.loginResponse.observe(viewLifecycleOwner) {


            if (!it.accessToken.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Login success ! ", Toast.LENGTH_SHORT).show()

            }

            Log.d("TAG", "Token: $it ")


        }


    }

    private fun handleLogin(email: String, password: String) {

        val requestLogin = RequestLogin(email = email, password = password)

        viewmodel.login(requestLogin)


    }


}