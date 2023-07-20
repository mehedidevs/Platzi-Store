package com.mehedi.platzistore.model.data.file


import com.google.gson.annotations.SerializedName

data class ResponseFile(
    @SerializedName("filename")
    var filename: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("originalname")
    var originalname: String?
)