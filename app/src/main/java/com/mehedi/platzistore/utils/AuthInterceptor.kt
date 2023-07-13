package com.mehedi.platzistore.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var prefsManager: PrefsManager


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${prefsManager.getPrefs(TOKEN_KEY)}")
        return chain.proceed(request.build())

    }
}