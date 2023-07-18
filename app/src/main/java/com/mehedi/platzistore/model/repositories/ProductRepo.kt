package com.mehedi.platzistore.model.repositories

import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.product.ResponseProduct
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import com.mehedi.platzistore.services.AuthService
import com.mehedi.platzistore.services.ProductService
import retrofit2.Response
import javax.inject.Inject

class ProductRepo @Inject constructor(private val service: ProductService) {


    suspend fun getAllProduct(): Response<List<ResponseProduct>> {

        return service.getAllProduct()

    }

    suspend fun getProductByID(id: Int): Response<ResponseProduct> {

        return service.getProductByID(id)

    }


}