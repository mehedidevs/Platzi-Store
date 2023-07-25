package com.mehedi.platzistore.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mehedi.platzistore.R

import com.mehedi.platzistore.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductFragment : Fragment(), ProductAdapter.Listener, ProductAdapterPaging.Listener {
    lateinit var binding: FragmentProductBinding

    private val viewModel: ProductViewModel by activityViewModels()
    lateinit var adapter: ProductAdapterPaging

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        viewModel.getAllProduct()

        adapter = ProductAdapterPaging(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.data.observe(viewLifecycleOwner) {

            lifecycleScope.launch {
                adapter.submitData(it)
                binding.productRcv.adapter = adapter
            }


        }


    }


//        viewModel.productResponse.observe(viewLifecycleOwner) {
//
//            if (it.isSuccessful) {
//                val adapter = ProductAdapter(this)
//                adapter.submitList(it.body())
//                binding.productRcv.adapter = adapter
//            }
//
//
//        }


    override fun productClick(productId: Int) {

        viewModel.setClickedProductID(productId)

        findNavController().navigate(R.id.action_productFragment_to_productFragmentSingle)


    }


}