package com.haidev.moviecatalogueapp.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.BuildConfig
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.databinding.ActivityDetailTvShowBinding
import com.haidev.moviecatalogueapp.ui.base.BaseActivity
import com.haidev.moviecatalogueapp.utils.enum.Status
import com.haidev.moviecatalogueapp.utils.gone
import com.haidev.moviecatalogueapp.utils.observeActivity
import com.haidev.moviecatalogueapp.utils.visible
import org.koin.android.ext.android.inject

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding, DetailTvShowViewModel>(),
    DetailTvShowNavigator {
    private val detailTvShowViewModel: DetailTvShowViewModel by inject()

    private lateinit var detailTvShowGenresAdapter: DetailTvShowGenresAdapter
    private lateinit var detailTvShowProductionAdapter: DetailTvShowProductionAdapter
    private var _binding: ActivityDetailTvShowBinding? = null
    private val binding get() = _binding
    private var skeletonGenres: Skeleton? = null
    private var skeletonProduction: Skeleton? = null
    private lateinit var tvshow: ListTvShow.Response.Result
    private lateinit var detailTvshow: DetailTvShow.Response
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        detailTvShowViewModel.navigator = this
        initSkeleton()
        initView()
    }

    private fun initCheckFavorite() {
        observeActivity(detailTvShowViewModel.getFavoriteTvSHow(tvshow.id)) {
            isFavorite = if (it?.id == tvshow.id) {
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
        tvshow =
            intent.getParcelableExtra<ListTvShow.Response.Result>(EXTRA_TV) as ListTvShow.Response.Result
        initCheckFavorite()
        binding?.ivBackdrop?.let {
            Glide.with(this).load(BuildConfig.API_URL_IMAGE + tvshow.backdrop_path)
                .into(it)
        }
        binding?.ivPoster?.let {
            Glide.with(this).load(BuildConfig.API_URL_IMAGE + tvshow.poster_path)
                .into(it)
        }
        binding?.tvTitle?.text = tvshow.name
        binding?.rating?.rating = (tvshow.vote_average?.div(2)?.toFloat() ?: 0.0) as Float
        binding?.tvRating?.text = "(${tvshow.vote_average})"
        binding?.tvOverview?.text = tvshow.overview
        binding?.tvReleaseDate?.text = tvshow.first_air_date
        binding?.btnBack?.setOnClickListener {
            finish()
        }

        binding?.btnShare?.setOnClickListener {
            val text = getString(
                R.string.share_tv_show,
                tvshow.name,
                tvshow.vote_average.toString(),
                tvshow.original_language
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
            if (this::detailTvshow.isInitialized) {
                if (isFavorite) {
                    detailTvShowViewModel.deleteFavoriteTvSHow(detailTvshow)
                } else {
                    detailTvShowViewModel.addFavoriteTvSHow(detailTvshow)
                }
            } else {
                Toast.makeText(this, R.string.text_waiting_loaded, Toast.LENGTH_SHORT).show()
            }

            initCheckFavorite()
        }
    }

    private fun initListAdapter() {
        binding?.rvGenres?.apply {
            setHasFixedSize(true)
            detailTvShowGenresAdapter = DetailTvShowGenresAdapter()
            adapter = detailTvShowGenresAdapter
        }
        binding?.rvProduction?.apply {
            setHasFixedSize(true)
            detailTvShowProductionAdapter = DetailTvShowProductionAdapter()
            adapter = detailTvShowProductionAdapter
        }
    }

    override fun onObserveAction() {
        with(detailTvShowViewModel) {
            observeActivity(dataDetailTvShow, ::handleDetailTvShowData)
        }
    }

    private fun handleDetailTvShowData(resource: Resource<DetailTvShow.Response>?) {
        when (resource?.status) {
            Status.LOADING -> {
                showLoading(true)
            }
            Status.SUCCESS -> {
                showLoading(false)
                detailTvshow = resource.data!!
                if (resource.data.genres?.isNotEmpty() == true) {
                    resource.data.genres.let {
                        detailTvShowGenresAdapter.setData(it)
                    }
                } else {
                    binding?.rvLoadingGenres?.gone()
                    binding?.rvGenres?.gone()
                    binding?.tvTitleGenres?.gone()
                }

                if (resource.data.production_companies?.isNotEmpty() == true) {
                    resource.data.production_companies.let {
                        detailTvShowProductionAdapter.setData(it)
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

    override fun setLayout() = R.layout.activity_detail_tv_show

    override fun getViewModels() = detailTvShowViewModel

    override fun onReadyAction() {
        initListAdapter()
        detailTvShowViewModel.getDetailTvShowAsync(tvshow.id.toString())
    }

    companion object {
        const val EXTRA_TV = "ExtraTv"
    }
}