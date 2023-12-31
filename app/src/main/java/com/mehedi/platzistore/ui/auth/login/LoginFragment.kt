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
import com.mehedi.platzistore.base.BaseFragment
import com.mehedi.platzistore.databinding.FragmentLoginBinding
import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.utils.PrefsManager
import com.mehedi.platzistore.utils.REFRESH_KEY
import com.mehedi.platzistore.utils.TOKEN_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate

){


    private val viewmodel: LoginViewModel by viewModels()


    @Inject
    lateinit var prefsManager: PrefsManager





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {

            val email = binding.userEmail?.text.toString()
            val password = binding.password.text.toString()


            // handleLogin(email, password)
            handleLogin("john@mail.com", "changeme")

        }


        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)


        }



        viewmodel.loginResponse.observe(viewLifecycleOwner) {


            if (!it.accessToken.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Login success ! ", Toast.LENGTH_SHORT).show()

                prefsManager.setPrefs(TOKEN_KEY, it.accessToken.toString())
                prefsManager.setPrefs(REFRESH_KEY, it.refreshToken.toString())


                findNavController().navigate(R.id.action_loginFragment_to_productFragment)

            }

            Log.d("TAG", "Token: $it ")


        }


    }

    private fun handleLogin(email: String, password: String) {

        val requestLogin = RequestLogin(email = email, password = password)

        viewmodel.login(requestLogin)


    }


}