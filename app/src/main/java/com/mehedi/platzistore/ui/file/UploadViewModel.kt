package com.mehedi.platzistore.ui.file

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.file.ResponseFile
import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.product.ResponseProduct
import com.mehedi.platzistore.model.data.profile.ResponseProfile
import com.mehedi.platzistore.model.repositories.AuthRepo
import com.mehedi.platzistore.model.repositories.FileRepo
import com.mehedi.platzistore.model.repositories.ProductRepo
import com.mehedi.platzistore.model.repositories.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private val repo: FileRepo) : ViewModel() {

    private val _response = MutableLiveData<Response<ResponseFile>>()

    val uploadResponse: LiveData<Response<ResponseFile>> = _response


    fun uploadFile(part: MultipartBody.Part) {

        viewModelScope.launch {
            _response.postValue(repo.uploadFile(part))
        }


    }


}