package com.mehedi.platzistore.model.repositories

import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import com.mehedi.platzistore.model.data.token.RequestToken
import com.mehedi.platzistore.model.data.token.ResponseToken
import com.mehedi.platzistore.services.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthRepo @Inject constructor(private val authService: AuthService) {


    suspend fun login(requestLogin: RequestLogin): ResponseLogin {

        val response = authService.login(requestLogin)

        return response.body()!!

    }


    suspend fun register(request: RequestRegister): Response<ResponseRegister> {


        return authService.register(request)

    }



    suspend fun token(request: RequestToken): Response<ResponseToken> {

        return authService.token(request)

    }


}