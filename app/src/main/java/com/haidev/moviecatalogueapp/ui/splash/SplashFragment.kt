package com.haidev.moviecatalogueapp.ui.splash

import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.FragmentSplashBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.goToHome
import com.haidev.moviecatalogueapp.utils.mainNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(),
    SplashNavigator {

    private val splashViewModel: SplashViewModel by viewModel()
    private lateinit var binding: FragmentSplashBinding

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        splashViewModel.navigator = this
    }

    override fun setLayout() = R.layout.fragment_splash

    override fun getViewModels() = splashViewModel

    override fun onResume() {
        super.onResume()
        launch {
            splashViewModel.displaySplashAsync().await()
        }
    }

    override fun navigateToHome() {
        mainNavController().goToHome(
            popUpToIdRes = R.id.splashFragment,
            isInclusive = true
        )
    }

    override fun onReadyAction() {}
}