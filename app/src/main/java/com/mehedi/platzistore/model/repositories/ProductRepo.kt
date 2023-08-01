package com.mehedi.platzistore.model.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.paging.PagingSource
import com.mehedi.platzistore.services.ProductService
import retrofit2.Response
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val service: ProductService,

    ) {


//    val data = Pager(
//        PagingConfig(
//            pageSize = 10,
//            initialLoadSize = 10
//        )
//    ) {
//
//        PagingSource(service)
//
//
//    }


    fun getData() = Pager(
        config = PagingConfig(pageSize = 10,initialLoadSize = 10),
        pagingSourceFactory = {
            PagingSource(service)
        }
    ).liveData


    suspend fun getAllProduct(): Response<List<ResponseProductItem>> {

        return service.getAllProduct()

    }

    suspend fun getProductByID(id: Int): Response<ResponseProductItem> {

        return service.getProductByID(id)

    }


}