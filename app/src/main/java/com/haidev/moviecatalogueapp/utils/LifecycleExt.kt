package com.haidev.moviecatalogueapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> Fragment.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { action.invoke(it) })
}

fun <T> AppCompatActivity.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { action.invoke(it) })
}