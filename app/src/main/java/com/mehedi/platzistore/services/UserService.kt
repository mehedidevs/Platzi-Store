package com.mehedi.platzistore.services

import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.profile.ResponseProfile
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @GET("auth/profile")
    suspend fun userProfile(): Response<ResponseProfile>




}