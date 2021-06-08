package com.haidev.moviecatalogueapp.ui.tvshow

import android.os.Bundle
import android.view.MenuItem
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.ActivityDetailTvShowBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding, DetailTvShowViewModel>(),
    DetailTvShowNavigator {

    private val detailTvShowViewModel: DetailTvShowViewModel by inject()
    private lateinit var binding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        detailTvShowViewModel.navigator = this
        binding.lifecycleOwner = this
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarDetailTvShow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setLayout() = R.layout.activity_detail_tv_show

    override fun getViewModels() = detailTvShowViewModel

    override fun onReadyAction() {}
}