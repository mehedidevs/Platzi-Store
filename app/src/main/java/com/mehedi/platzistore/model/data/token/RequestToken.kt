package com.mehedi.platzistore.model.data.token


import com.google.gson.annotations.SerializedName

data class RequestToken(
    @SerializedName("refreshToken")
    var refreshToken: String?
)