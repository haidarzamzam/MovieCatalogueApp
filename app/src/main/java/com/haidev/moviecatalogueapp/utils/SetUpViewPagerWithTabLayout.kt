package com.haidev.moviecatalogueapp.utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SetUpViewPagerWithTabLayout(
    fragment: Fragment,
    private val listFragment: List<Pair<Fragment, String>>,
    viewPager: ViewPager2,
    tabLayout: TabLayout,
    private val onPageSelectedListener: (Int) -> Unit = {}
) : FragmentStateAdapter(fragment) {

    init {
        viewPager.adapter = this
        viewPager.offscreenPageLimit = 3
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listFragment[position].second
        }.attach()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onPageSelectedListener(position)
            }
        })
    }

    override fun getItemCount() = listFragment.size

    override fun createFragment(position: Int) = listFragment[position].first
}