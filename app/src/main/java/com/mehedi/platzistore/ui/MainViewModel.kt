package com.mehedi.platzistore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.token.RequestToken
import com.mehedi.platzistore.model.data.token.ResponseToken
import com.mehedi.platzistore.model.repositories.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    private val _tokenResponse = MutableLiveData<Response<ResponseToken>>()

    val tokenResponse: LiveData<Response<ResponseToken>> = _tokenResponse


    fun token(request: RequestToken) {

        viewModelScope.launch {
            _tokenResponse.postValue(repo.token(request))
        }


    }


}