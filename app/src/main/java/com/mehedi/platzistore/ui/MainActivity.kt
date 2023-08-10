package com.mehedi.platzistore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.mehedi.platzistore.R
import com.mehedi.platzistore.model.data.token.RequestToken
import com.mehedi.platzistore.utils.PrefsManager
import com.mehedi.platzistore.utils.REFRESH_KEY
import com.mehedi.platzistore.utils.TOKEN_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = RequestToken(prefsManager.getPrefs(REFRESH_KEY))
        Log.d("TAG", "old Token:$token ")
        viewModel.token(token)


        viewModel.tokenResponse.observe(this) {
            if (it.isSuccessful) {

                prefsManager.setPrefs(TOKEN_KEY, it.body()?.accessToken.toString())
                prefsManager.setPrefs(REFRESH_KEY, it.body()?.refreshToken.toString())

                Log.d("TAG", "new Token:${it.body()?.accessToken.toString()} ")


            } else {

                Log.d("TAG", "onCreate:${it.errorBody()} ")
            }

        }


    }
}