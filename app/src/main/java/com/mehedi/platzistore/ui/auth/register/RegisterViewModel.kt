package com.mehedi.platzistore.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.resgister.RequestRegister
import com.mehedi.platzistore.model.data.resgister.ResponseRegister
import com.mehedi.platzistore.model.repositories.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    private val _registerResponse = MutableLiveData<Response<ResponseRegister>>()

    val registerResponse: LiveData<Response<ResponseRegister>> = _registerResponse


    fun register(request: RequestRegister) {

        viewModelScope.launch {
            _registerResponse.postValue(repo.register(request))
        }


    }


}