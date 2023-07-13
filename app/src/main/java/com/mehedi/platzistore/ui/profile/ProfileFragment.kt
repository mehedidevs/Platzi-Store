package com.mehedi.platzistore.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels


import com.mehedi.platzistore.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile()

        viewModel.profileResponse.observe(viewLifecycleOwner) {

            Log.d("TAG", "${it.message()}: ")
            Log.d("TAG", "${it.code()}: ")
            Log.d("TAG", "${it.body()}: ")
            Log.d("TAG", "${it.errorBody()}: ")

            if (it.isSuccessful) {

                it.body()?.let { profile ->
                    binding.userName.text = profile.name

                }


            }


        }


    }

}