package com.haidev.moviecatalogueapp.ui.tvshow

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
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
    private val binding get() = _binding!!
    private lateinit var skeletonGenres: Skeleton
    private lateinit var skeletonProduction: Skeleton
    private lateinit var tvshow: ListTvShow.Response.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailTvShowViewModel.navigator = this
        initSkeleton()
        initView()
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
        tvshow =
            intent.getParcelableExtra<ListTvShow.Response.Result>(EXTRA_TV) as ListTvShow.Response.Result
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${tvshow.backdrop_path}")
            .into(binding.ivBackdrop)
        Glide.with(this).load("https://image.tmdb.org/t/p/w400/${tvshow.poster_path}")
            .into(binding.ivPoster)
        binding.tvTitle.text = tvshow.name
        binding.rating.rating = tvshow.vote_average.div(2).toFloat()
        binding.tvRating.text = "(${tvshow.vote_average})"
        binding.tvOverview.text = tvshow.overview
        binding.tvReleaseDate.text = tvshow.first_air_date
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Tv Show dengan judul \"${tvshow.name}\" dengan rating \"${tvshow.vote_average}\" dan menggunakan bahasa \"${tvshow.original_language}\""
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initListAdapter() {
        binding.rvGenres.apply {
            setHasFixedSize(true)
            detailTvShowGenresAdapter = DetailTvShowGenresAdapter()
            adapter = detailTvShowGenresAdapter
        }
        binding.rvProduction.apply {
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
                if (resource.data?.genres?.isNotEmpty() == true) {
                    resource.data.genres.let {
                        detailTvShowGenresAdapter.setData(it)
                    }
                } else {
                    binding.rvLoadingGenres.gone()
                    binding.rvGenres.gone()
                    binding.tvTitleGenres.gone()
                }

                if (resource.data?.production_companies?.isNotEmpty() == true) {
                    resource.data.production_companies.let {
                        detailTvShowProductionAdapter.setData(it)
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