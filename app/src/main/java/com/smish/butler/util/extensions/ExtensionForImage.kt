package com.smish.butler.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.smish.butler.R

fun ImageView.loadImageWithGlide(url: Any?) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .error(R.drawable.ic_round_photo_24)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
//        .placeholder(R.drawable.movie_loading_animation)
        .into(this)
}