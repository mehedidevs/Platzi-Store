package com.mehedi.platzistore.model.repositories

import com.mehedi.platzistore.model.data.file.ResponseFile
import com.mehedi.platzistore.services.UploadService
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class FileRepo @Inject constructor(private val service: UploadService) {


    suspend fun uploadFile(part: MultipartBody.Part): Response<ResponseFile> {

        return service.uploadFile(part)

    }


}