package com.mehedi.platzistore.model.data.token


import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("access_token")
    var accessToken: String?,
    @SerializedName("refresh_token")
    var refreshToken: String?
)