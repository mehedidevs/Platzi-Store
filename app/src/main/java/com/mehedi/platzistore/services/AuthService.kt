package com.mehedi.platzistore.services

import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import com.mehedi.platzistore.model.data.token.RequestToken
import com.mehedi.platzistore.model.data.token.ResponseToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body requestLogin: RequestLogin): Response<ResponseLogin>


    @POST("users/")
    suspend fun register(@Body request: RequestRegister): Response<ResponseRegister>


    @POST("auth/refresh-token")
    suspend fun token(@Body request: RequestToken): Response<ResponseToken>

}