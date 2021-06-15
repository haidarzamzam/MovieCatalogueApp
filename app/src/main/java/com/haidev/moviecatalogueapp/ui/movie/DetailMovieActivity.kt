package com.haidev.moviecatalogueapp.ui.movie

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.databinding.ActivityDetailMovieBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import com.haidev.moviecatalogueapp.utils.enum.Status
import com.haidev.moviecatalogueapp.utils.gone
import com.haidev.moviecatalogueapp.utils.observeActivity
import com.haidev.moviecatalogueapp.utils.visible
import org.koin.android.ext.android.inject

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(),
    DetailMovieNavigator {
    private val detailMovieViewModel: DetailMovieViewModel by inject()

    private lateinit var detailMovieGenresAdapter: DetailMovieGenresAdapter
    private lateinit var detailMovieProductionAdapter: DetailMovieProductionAdapter
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var skeletonGenres: Skeleton
    private lateinit var skeletonProduction: Skeleton
    private lateinit var movie: ListMovie.Response.Result
    private lateinit var detailMovie: DetailMovie.Response
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailMovieViewModel.navigator = this
        initSkeleton()
        initView()
    }

    private fun initCheckFavorite() {
        observeActivity(detailMovieViewModel.getFavoriteMovie(movie.id)) {
            isFavorite = if (it?.id == movie.id) {
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_select_24)
                true
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_unselect_24)
                false
            }
        }
    }

    private fun initSkeleton() {
        skeletonGenres =
            binding.rvLoadingGenres.applySkeleton(R.layout.item_row_skeleton_genres, 4)
        skeletonProduction = binding.rvLoadingProduction.applySkeleton(
            R.layout.item_row_skeleton_production,
            4
        )
    }

    private fun initView() {
        movie =
            intent.getParcelableExtra<ListMovie.Response.Result>(EXTRA_MOVIE) as ListMovie.Response.Result
        initCheckFavorite()
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

        binding.btnFavorite.setOnClickListener {
            if (isFavorite) {
                detailMovieViewModel.deleteFavoriteMovie(detailMovie)
            } else {
                detailMovieViewModel.addFavoriteMovie(detailMovie)
            }

            initCheckFavorite()
        }
    }


    private fun initListAdapter() {
        binding.rvGenres.apply {
            setHasFixedSize(true)
            detailMovieGenresAdapter = DetailMovieGenresAdapter()
            adapter = detailMovieGenresAdapter
        }
        binding.rvProduction.apply {
            setHasFixedSize(true)
            detailMovieProductionAdapter = DetailMovieProductionAdapter()
            adapter = detailMovieProductionAdapter
        }
    }

    override fun onObserveAction() {
        with(detailMovieViewModel) {
            observeActivity(dataDetailMovie, ::handleDetailMovieData)
        }
    }

    private fun handleDetailMovieData(resource: Resource<DetailMovie.Response>?) {
        when (resource?.status) {
            Status.LOADING -> {
                showLoading(true)
            }
            Status.SUCCESS -> {
                showLoading(false)
                detailMovie = resource.data!!
                if (resource.data.genres?.isNotEmpty() == true) {
                    resource.data.genres.let {
                        detailMovieGenresAdapter.setData(it)
                    }
                } else {
                    binding.rvLoadingGenres.gone()
                    binding.rvGenres.gone()
                    binding.tvTitleGenres.gone()
                }

                if (resource.data.production_companies?.isNotEmpty() == true) {
                    resource.data.production_companies.let {
                        detailMovieProductionAdapter.setData(it)
                    }
                } else {
                    binding.rvLoadingProduction.gone()
                    binding.rvProduction.gone()
                    binding.tvTitleProduction.gone()
                }

                binding.executePendingBindings()
            }
            else -> {
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        skeletonGenres.showSkeleton()
        skeletonProduction.showSkeleton()
        with(binding) {
            if (isLoading) {
                rvLoadingGenres.visible()
                rvLoadingProduction.visible()
            } else {
                rvLoadingGenres.gone()
                rvLoadingProduction.gone()
            }
        }
    }

    override fun setLayout() = R.layout.activity_detail_movie

    override fun getViewModels() = detailMovieViewModel

    override fun onReadyAction() {
        initListAdapter()
        detailMovieViewModel.getDetailMovieAsync(movie.id.toString())
    }

    companion object {
        const val EXTRA_MOVIE = "ExtraMovie"
    }
}