package com.mehedi.platzistore.model.repositories

import com.mehedi.platzistore.model.data.profile.ResponseProfile
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import com.mehedi.platzistore.services.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(private val service: UserService) {


    suspend fun userProfile(): Response<ResponseProfile> {

        return service.userProfile()

    }


}