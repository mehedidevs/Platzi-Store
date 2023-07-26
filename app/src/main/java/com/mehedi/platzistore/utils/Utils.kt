package com.mehedi.platzistore.utils


import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


const val BASE_URL = "https://api.escuelajs.co/api/v1/"
const val TOKEN_KEY = "access_token_key"

fun ImageView.load(imageUrl: String) {

//    val requestOptions: RequestOptions = RequestOptions()
//        .placeholder(com.mehedi.platzistore.R.drawable.pls)
//        .error(com.mehedi.platzistore.R.drawable.pls_error) // Replace this with your error resource id if you want to display an error image on failure
//        .diskCacheStrategy(DiskCacheStrategy.ALL)

    this.load(imageUrl) {
        this.placeholder(com.mehedi.platzistore.R.drawable.pls)
        this.error(com.mehedi.platzistore.R.drawable.pls_error) // Replace this with your error resource id if you want to display an error image on failure

        this.diskCachePolicy(CachePolicy.ENABLED)

    }


}


fun View.slideUp(duration: Int = 500) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Int = 500) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}
