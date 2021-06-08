package com.haidev.moviecatalogueapp.ui.home

import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.FragmentHomeBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    HomeNavigator {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun setLayout() = R.layout.fragment_home

    override fun getViewModels() = homeViewModel

    override fun onReadyAction() {}

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        homeViewModel.navigator = this
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val sectionsPagerAdapter =
            activity?.let { SectionsPagerAdapter(this.requireContext(), it.supportFragmentManager) }
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}