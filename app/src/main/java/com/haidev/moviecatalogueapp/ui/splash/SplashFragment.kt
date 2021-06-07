package com.haidev.moviecatalogueapp.ui.splash

import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.FragmentSplashBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(false),
    SplashNavigator {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun setLayout(): Int {
        return R.layout.fragment_splash
    }

    override fun getViewModels() = splashViewModel

    override fun onReadyAction() {}

    override fun navigateToMainApp() {
    }
}