package com.haidev.moviecatalogueapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.ActivityHomeBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import com.haidev.moviecatalogueapp.ui.favorite.FavoriteActivity
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),
    HomeNavigator {

    private val homeViewModel: HomeViewModel by inject()
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        homeViewModel.navigator = this
        binding?.lifecycleOwner = this
        setUpBottomView()
        setUpFAB()
    }

    override fun setLayout() = R.layout.activity_home

    override fun getViewModels() = homeViewModel

    private fun setUpFAB() {
        binding?.fabFavorite?.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpBottomView() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding?.bottomNavigatinView?.setupWithNavController(navController)
    }
}