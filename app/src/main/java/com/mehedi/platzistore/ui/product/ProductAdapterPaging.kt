package com.mehedi.platzistore.ui.product

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView


import com.mehedi.platzistore.databinding.ItemProductBinding
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.utils.load

class ProductAdapterPaging(var listener: Listener) :
    PagingDataAdapter<ResponseProductItem, ProductAdapterPaging.ProductViewHolder>(COMPARATOR) {

    interface Listener {
        fun productClick(productId: Int)
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<ResponseProductItem>() {
            override fun areItemsTheSame(
                oldItem: ResponseProductItem, newItem: ResponseProductItem
            ): Boolean {

                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseProductItem, newItem: ResponseProductItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),

            )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {


            holder.binding.titleTextView.text = "${it.id} ${it.title}"
            holder.binding.descriptionTextView.text = it.description
            holder.binding.priceTextView.text = "Price : $${it.price}"


            for (i in it.images.indices) {

                Log.i("TAG", "index: $i ")
                when (i) {
                    0 -> {
                        holder.binding.image1.load(it.images[i]!!)
                    }

                    1 -> {
                        holder.binding.image2.load(it.images[i]!!)
                    }

                    2 -> {
                        holder.binding.image3.load(it.images[i]!!)
                    }

                }


                it.images[0]?.let { img_url ->
                    // holder.binding.image1.load(img_url)
                }
            }




            it.category?.let { ctg ->
                holder.binding.categoryNameTextView.text = ctg.name
                holder.binding.categoryImageView.load(ctg.image!!)

            }

            holder.itemView.setOnClickListener { _ ->

                listener.productClick(it.id)


            }


        }
    }

}