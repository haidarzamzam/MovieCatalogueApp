package com.haidev.moviecatalogueapp.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

@BindingAdapter("srcGlide")
fun ImageView.setGlideImage(imageUrl: String?) {
    imageUrl?.let {
        if (it.substringAfterLast(".").equals("svg", true))
            GlideToVectorYou.justLoadImage(
                getParentActivity(),
                Uri.parse("https://image.tmdb.org/t/p/w500/$it"),
                this
            )
        else
            Glide.with(context).load("https://image.tmdb.org/t/p/w200/$it").into(this)
    }
}