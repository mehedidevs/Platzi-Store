package com.mehedi.platzistore.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mehedi.platzistore.R

import com.mehedi.platzistore.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductFragment : Fragment(), ProductAdapter.Listener {
    lateinit var binding: FragmentProductBinding

    private val viewModel: ProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        viewModel.getAllProduct()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productResponse.observe(viewLifecycleOwner) {

            if (it.isSuccessful) {
                val adapter = ProductAdapter(this)
                adapter.submitList(it.body())
                binding.productRcv.adapter = adapter
            }


        }


    }

    override fun productClick(productId: Int) {

        viewModel.setClickedProductID(productId)

        findNavController().navigate(R.id.action_productFragment_to_productFragmentSingle)


    }


}