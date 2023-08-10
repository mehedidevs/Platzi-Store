package com.mehedi.platzistore.ui.auth.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.mehedi.platzistore.R
import com.mehedi.platzistore.base.BaseFragment
import com.mehedi.platzistore.databinding.FragmentLoginBinding
import com.mehedi.platzistore.databinding.FragmentRegisterBinding
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private val viewModel: RegisterViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.registerBtn.setOnClickListener {
            val name = binding.userName.text.toString()
            val email = binding.userEmail.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val imageAvatar = "https://dmarkcy.com/images/team/web/mehedi.png"


            handleRegister(name, email, password, imageAvatar)

        }


        viewModel.registerResponse.observe(viewLifecycleOwner) {

            if (it.isSuccessful) {

                Toast.makeText(requireContext(), "User Create success ! ", Toast.LENGTH_SHORT)
                    .show()

                Log.d("TAG", "User : ${it.body()} ")

            }


        }


    }

    private fun handleRegister(name: String, email: String, password: String, imageAvatar: String) {

        val request =
            RequestRegister(name = name, password = password, email = email, avatar = imageAvatar)


        viewModel.register(request)


    }


}