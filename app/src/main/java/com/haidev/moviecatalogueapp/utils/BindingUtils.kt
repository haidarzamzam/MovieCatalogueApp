package com.haidev.moviecatalogueapp.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.haidev.moviecatalogueapp.BuildConfig

@BindingAdapter("srcGlide")
fun ImageView.setGlideImage(imageUrl: String?) {
    imageUrl?.let {
        if (it.substringAfterLast(".").equals("svg", true))
            GlideToVectorYou.justLoadImage(
                getParentActivity(),
                Uri.parse(
                    BuildConfig.API_URL_IMAGE + it
                ),
                this
            )
        else
            Glide.with(context).load(BuildConfig.API_URL_IMAGE + it).into(this)
    }
}