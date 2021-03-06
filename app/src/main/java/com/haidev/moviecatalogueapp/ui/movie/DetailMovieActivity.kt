package com.haidev.moviecatalogueapp.ui.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.BuildConfig
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
    private var _binding: ActivityDetailMovieBinding? = null
    private val binding get() = _binding
    private var skeletonGenres: Skeleton? = null
    private var skeletonProduction: Skeleton? = null
    private lateinit var movie: ListMovie.Response.Result
    private lateinit var detailMovie: DetailMovie.Response
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        detailMovieViewModel.navigator = this
        initSkeleton()
        initView()
    }

    private fun initCheckFavorite() {
        observeActivity(detailMovieViewModel.getFavoriteMovie(movie.id)) {
            isFavorite = if (it?.id == movie.id) {
                binding?.btnFavorite?.setImageResource(R.drawable.ic_baseline_favorite_select_24)
                true
            } else {
                binding?.btnFavorite?.setImageResource(R.drawable.ic_baseline_favorite_unselect_24)
                false
            }
        }
    }

    private fun initSkeleton() {
        skeletonGenres =
            binding?.rvLoadingGenres?.applySkeleton(R.layout.item_row_skeleton_genres, 4)
        skeletonProduction = binding?.rvLoadingProduction?.applySkeleton(
            R.layout.item_row_skeleton_production,
            4
        )
    }

    private fun initView() {
        movie =
            intent.getParcelableExtra<ListMovie.Response.Result>(EXTRA_MOVIE) as ListMovie.Response.Result
        initCheckFavorite()
        binding?.ivBackdrop?.let {
            Glide.with(this).load(BuildConfig.API_URL_IMAGE + movie.backdrop_path)
                .into(it)
        }
        binding?.ivPoster?.let {
            Glide.with(this).load(BuildConfig.API_URL_IMAGE + movie.poster_path)
                .into(it)
        }
        binding?.tvTitle?.text = movie.title
        binding?.rating?.rating = (movie.vote_average?.div(2)?.toFloat() ?: 0.0) as Float
        binding?.tvRating?.text = "(${movie.vote_average})"
        binding?.tvOverview?.text = movie.overview
        binding?.tvReleaseDate?.text = movie.release_date
        binding?.btnBack?.setOnClickListener {
            finish()
        }

        binding?.btnShare?.setOnClickListener {
            val text = getString(
                R.string.share_movie,
                movie.title,
                movie.vote_average.toString(),
                movie.original_language
            )
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                text
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

        binding?.btnFavorite?.setOnClickListener {
            if (this::detailMovie.isInitialized) {
                if (isFavorite) {
                    detailMovieViewModel.deleteFavoriteMovie(detailMovie)
                } else {
                    detailMovieViewModel.addFavoriteMovie(detailMovie)
                }
            } else {
                Toast.makeText(this, getString(R.string.text_waiting_loaded), Toast.LENGTH_SHORT)
                    .show()
            }

            initCheckFavorite()
        }
    }


    private fun initListAdapter() {
        binding?.rvGenres?.apply {
            setHasFixedSize(true)
            detailMovieGenresAdapter = DetailMovieGenresAdapter()
            adapter = detailMovieGenresAdapter
        }
        binding?.rvProduction?.apply {
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
                if (detailMovie.genres?.isNotEmpty() == true) {
                    detailMovie.genres.let {
                        it?.let { it1 -> detailMovieGenresAdapter.setData(it1) }
                    }
                } else {
                    binding?.rvLoadingGenres?.gone()
                    binding?.rvGenres?.gone()
                    binding?.tvTitleGenres?.gone()
                }

                if (detailMovie.production_companies?.isNotEmpty() == true) {
                    detailMovie.production_companies.let {
                        it?.let { it1 -> detailMovieProductionAdapter.setData(it1) }
                    }
                } else {
                    binding?.rvLoadingProduction?.gone()
                    binding?.rvProduction?.gone()
                    binding?.tvTitleProduction?.gone()
                }

                binding?.executePendingBindings()
            }
            else -> {
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        skeletonGenres?.showSkeleton()
        skeletonProduction?.showSkeleton()
        with(binding) {
            if (isLoading) {
                this?.rvLoadingGenres?.visible()
                this?.rvLoadingProduction?.visible()
            } else {
                this?.rvLoadingGenres?.gone()
                this?.rvLoadingProduction?.gone()
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