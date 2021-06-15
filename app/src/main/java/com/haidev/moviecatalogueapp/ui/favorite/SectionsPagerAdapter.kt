package com.haidev.moviecatalogueapp.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.ui.favorite.menus.MovieFavoriteFragment
import com.haidev.moviecatalogueapp.ui.favorite.menus.TvShowFavoriteFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFavoriteFragment()
            1 -> TvShowFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.text_movie, R.string.text_tv_show)
    }
}