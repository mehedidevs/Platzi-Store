package com.mehedi.platzistore.network

import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(requestLogin: RequestLogin): Response<ResponseLogin>


}