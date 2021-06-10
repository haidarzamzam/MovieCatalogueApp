package com.haidev.moviecatalogueapp.ui.splash

import android.content.Intent
import android.os.Bundle
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.ActivitySplashBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import com.haidev.moviecatalogueapp.ui.home.HomeActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashNavigator {
    private val splashViewModel: SplashViewModel by inject()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        splashViewModel.navigator = this
    }

    override fun setLayout() = R.layout.activity_splash

    override fun getViewModels() = splashViewModel

    override fun onResume() {
        super.onResume()
        launch {
            splashViewModel.displaySplashAsync().await()
        }
    }

    override fun onReadyAction() {}

    override fun navigateToHome() {
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}