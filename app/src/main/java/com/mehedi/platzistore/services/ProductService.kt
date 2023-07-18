package com.mehedi.platzistore.services

import com.mehedi.platzistore.model.data.product.ResponseProduct
import com.mehedi.platzistore.model.data.profile.ResponseProfile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {


    @GET("products")
    suspend fun getAllProduct(): Response<List<ResponseProduct>>


    @GET("products/{id}")
    suspend fun getProductByID(
        @Path("id") id: Int
    ): Response<ResponseProduct>

}