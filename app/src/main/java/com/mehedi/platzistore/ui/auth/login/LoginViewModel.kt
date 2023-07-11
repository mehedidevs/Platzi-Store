package com.mehedi.platzistore.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.repositories.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {

    private val _loginResponse = MutableLiveData<ResponseLogin>()

    val loginResponse: LiveData<ResponseLogin> = _loginResponse


    fun login(requestLogin: RequestLogin) {

        viewModelScope.launch {
            _loginResponse.postValue(repo.login(requestLogin))
        }


    }


}