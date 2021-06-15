package com.haidev.moviecatalogueapp.ui.favorite

import android.os.Bundle
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.ActivityFavoriteBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding, FavoriteViewModel>(),
    FavoriteNavigator {
    private val favoriteViewModel: FavoriteViewModel by inject()
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        favoriteViewModel.navigator = this
        binding?.lifecycleOwner = this

        setupToolbar()
        setupTabView()
    }

    private fun setupToolbar() {
        binding?.toolbar?.inflateMenu(R.menu.menu_toolbar)
        binding?.toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.closeFavorite -> finish()
            }
            true
        }
    }

    private fun setupTabView() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)
    }

    override fun setLayout() = R.layout.activity_favorite

    override fun getViewModels() = favoriteViewModel
}