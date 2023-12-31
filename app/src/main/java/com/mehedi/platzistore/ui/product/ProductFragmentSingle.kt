package com.mehedi.platzistore.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.mehedi.platzistore.databinding.FragmentProductSingleBinding
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.utils.load
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductFragmentSingle : Fragment() {

    lateinit var binding: FragmentProductSingleBinding
    private val viewModel: ProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProductSingleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productID.observe(viewLifecycleOwner) {

            viewModel.getProductByID(it)


        }

        viewModel.productResponseSingle.observe(viewLifecycleOwner) {

            if (it.isSuccessful) {

                it.body()?.let { item ->
                    setProduct(item)
                }


            }

        }


    }

    private fun setProduct(it: ResponseProductItem) {


        binding.titleTextView.text = it.title
        binding.descriptionTextView.text = it.description
        binding.priceTextView.text = "Price : $${it.price}"


        for (i in it.images.indices) {

            Log.i("TAG", "index: $i ")
            when (i) {
                0 -> {
                    binding.image1.load(it.images[i]!!)
                }

                1 -> {
                    binding.image2.load(it.images[i]!!)
                }

                2 -> {
                    binding.image3.load(it.images[i]!!)
                }

            }


            it.images[0]?.let { img_url ->
                // holder.binding.image1.load(img_url)
            }
        }





        it.category?.let { ctg ->
            binding.categoryNameTextView.text = ctg.name
            binding.categoryImageView.load(ctg.image!!)

        }


    }


}