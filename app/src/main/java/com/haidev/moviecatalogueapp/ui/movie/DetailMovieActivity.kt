package com.haidev.moviecatalogueapp.ui.movie

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.databinding.ActivityDetailMovieBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(),
    DetailMovieNavigator {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private val detailMovieViewModel: DetailMovieViewModel by inject()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailMovieViewModel.navigator = this
        initView()
    }

    private fun initView() {
        val movie =
            intent.getParcelableExtra<ListMovie.Response.Result>(EXTRA_MOVIE) as ListMovie.Response.Result
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${movie.backdrop_path}")
            .into(binding.ivBackdrop)
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${movie.poster_path}")
            .into(binding.ivPoster)
        binding.tvTitle.text = movie.title
        binding.rating.rating = movie.vote_average.div(2).toFloat()
        binding.tvRating.text = "(${movie.vote_average})"
        binding.tvOverview.text = movie.overview
        binding.tvReleaseDate.text = movie.release_date
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Film dengan judul \"${movie.title}\" dengan rating \"${movie.vote_average}\" dan menggunakan bahasa \"${movie.original_language}\""
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    override fun setLayout() = R.layout.activity_detail_movie

    override fun getViewModels() = detailMovieViewModel

    override fun onReadyAction() {

    }
}