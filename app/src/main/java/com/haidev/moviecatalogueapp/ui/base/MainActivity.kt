package com.haidev.moviecatalogueapp.ui.base

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.haidev.moviecatalogueapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setContentView(R.layout.activity_main)
    }
}