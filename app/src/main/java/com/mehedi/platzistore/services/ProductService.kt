package com.mehedi.platzistore.services

import com.mehedi.platzistore.model.data.product.ResponseProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {


    @GET("products")
    suspend fun getAllProduct(): Response<List<ResponseProductItem>>

    @GET("products/")
    suspend fun getAllProductPaging(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<ResponseProductItem>


    @GET("products/{id}")
    suspend fun getProductByID(
        @Path("id") id: Int
    ): Response<ResponseProductItem>

}