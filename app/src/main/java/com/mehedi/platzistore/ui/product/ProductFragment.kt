package com.mehedi.platzistore.ui.product

import android.R
import android.os.Bundle
import android.service.media.MediaBrowserService.BrowserRoot
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.mehedi.platzistore.databinding.FragmentProductBinding
import com.mehedi.platzistore.utils.slideDown
import dagger.hilt.android.AndroidEntryPoint
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
        binding.productRcv.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.btnCompose.shrink()
                  // binding.bottomNavigationView.slideDown(100)
                binding.bottomNavigationView.visibility = View.GONE
                   // toggle(false)
                  //  Toast.makeText(requireContext(), "Scrolling up", Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(requireContext(), "Scrolling down", Toast.LENGTH_SHORT).show()
                    //toggle(true)
                 //   binding.bottomNavigationView.slideDown(100)
                    binding.btnCompose.extend()
                   binding.bottomNavigationView.visibility = View.VISIBLE
                }

            }
        })


        viewModel.data.observe(viewLifecycleOwner) {

            lifecycleScope.launch {
                adapter.submitData(it)

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

        findNavController().navigate(com.mehedi.platzistore.R.id.action_productFragment_to_productFragmentSingle)


    }

//    private fun toggle(show: Boolean) {
//
//
//        val transition: Transition = Slide(Gravity.BOTTOM)
//        transition.duration = 10
//        transition.addTarget(binding.bottomNavigationView)
//        TransitionManager.beginDelayedTransition(binding.root, transition)
//        binding.bottomNavigationView.visibility = if (show) View.VISIBLE else View.GONE
//    }


}