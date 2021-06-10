package com.haidev.moviecatalogueapp.ui.tvshow

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.databinding.ActivityDetailTvShowBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding, DetailTvShowViewModel>(),
    DetailTvShowNavigator {

    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private val detailTvShowViewModel: DetailTvShowViewModel by inject()
    private lateinit var binding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailTvShowViewModel.navigator = this
        initView()
    }

    private fun initView() {
        val movie =
            intent.getParcelableExtra<ListTvShow.Response.Result>(DetailTvShowActivity.EXTRA_TV) as ListTvShow.Response.Result
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${movie.backdrop_path}")
            .into(binding.ivBackdrop)
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${movie.poster_path}")
            .into(binding.ivPoster)
        binding.tvTitle.text = movie.name
        binding.rating.rating = movie.vote_average.div(2).toFloat()
        binding.tvRating.text = "(${movie.vote_average})"
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Tv Show dengan judul \"${movie.name}\" dengan rating \"${movie.vote_average}\" dan menggunakan bahasa \"${movie.original_language}\""
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    override fun setLayout() = R.layout.activity_detail_tv_show

    override fun getViewModels() = detailTvShowViewModel

    override fun onReadyAction() {}
}