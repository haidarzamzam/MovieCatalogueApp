package com.haidev.moviecatalogueapp.ui.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.TIME_SPLASHSCREEN
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SplashViewModel(application: Application) :
    BaseViewModel<SplashNavigator>(application) {

    fun displaySplashAsync(): Deferred<Boolean> {
        return viewModelScope.async {
            delay(TIME_SPLASHSCREEN)
            navigator?.navigateToHome()
            return@async true
        }
    }
}