package com.mehedi.platzistore.ui.file

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.file.ResponseFile
import com.mehedi.platzistore.model.repositories.FileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
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