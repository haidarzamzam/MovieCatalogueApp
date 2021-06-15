package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.databinding.FragmentTvShowFavoriteBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.ui.tvshow.DetailTvShowActivity
import com.haidev.moviecatalogueapp.utils.observeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment :
    BaseFragment<FragmentTvShowFavoriteBinding, TvShowFavoriteViewModel>(),
    TvShowFavoriteNavigator {

    private val tvShowFavoriteViewModel: TvShowFavoriteViewModel by viewModel()
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowFavoriteListAdapter: TvShowFavoriteListAdapter
    private lateinit var skeleton: Skeleton

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding.lifecycleOwner = this
        tvShowFavoriteViewModel.navigator = this
        skeleton = binding.rvLoading.applySkeleton(R.layout.item_row_skeleton_list, 8)
    }

    override fun setLayout() = R.layout.fragment_tv_show_favorite

    override fun getViewModels() = tvShowFavoriteViewModel

    override fun onReadyAction() {
        initListTvShowAdapter()
        tvShowFavoriteViewModel.getAllFavoriteTvSHow()
    }

    override fun onObserveAction() {
        observeFragment(tvShowFavoriteViewModel.getAllFavoriteTvSHow()) {
            it.let {
                tvShowFavoriteListAdapter.setData(it as List<DetailTvShow.Response>)
            }
            binding.executePendingBindings()
        }
    }

    private fun initListTvShowAdapter() {
        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            tvShowFavoriteListAdapter = TvShowFavoriteListAdapter(this@TvShowFavoriteFragment)
            adapter = tvShowFavoriteListAdapter
        }
    }

    override fun navigateToDetailTvShow(data: DetailTvShow.Response) {
        val intent = Intent(activity?.applicationContext, DetailTvShowActivity::class.java)
        val listGenres = arrayListOf<Int>()
        data.genres?.forEach { it.id?.let { it1 -> listGenres.add(it1) } }
        val tvshow = data.id?.let {
            ListTvShow.Response.Result(
                data.backdrop_path,
                data.first_air_date,
                listGenres,
                it,
                data.name,
                data.origin_country,
                data.original_language,
                data.original_name,
                data.overview,
                data.popularity,
                data.poster_path,
                data.vote_average,
                data.vote_count
            )
        }
        intent.putExtra(DetailTvShowActivity.EXTRA_TV, tvshow)
        activity?.startActivity(intent)
    }

}