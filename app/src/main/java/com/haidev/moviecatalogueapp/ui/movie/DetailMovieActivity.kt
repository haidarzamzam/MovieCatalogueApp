package com.haidev.moviecatalogueapp.ui.movie

import android.os.Bundle
import android.view.MenuItem
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.databinding.ActivityDetailMovieBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(),
    DetailMovieNavigator {

    private val detailMovieViewModel: DetailMovieViewModel by inject()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        detailMovieViewModel.navigator = this
        binding.lifecycleOwner = this
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarDetailMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setLayout() = R.layout.activity_detail_movie

    override fun getViewModels() = detailMovieViewModel

    override fun onReadyAction() {}
}