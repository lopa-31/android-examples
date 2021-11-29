package com.example.googlesignin

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun bindImageFromUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_account_circle_24)
            )
            .into(imgView)
    }
}