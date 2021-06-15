package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import com.faltenreich.skeletonlayout.Skeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.databinding.FragmentTvShowFavoriteBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment :
    BaseFragment<FragmentTvShowFavoriteBinding, TvShowFavoriteViewModel>(),
    TvShowFavoriteNavigator {

    private val tvShowFavoriteViewModel: TvShowFavoriteViewModel by viewModel()
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowFavoriteListAdapter: TvShowFavoriteListAdapter
    private lateinit var skeleton: Skeleton

    override fun setLayout() = R.layout.fragment_tv_show_favorite

    override fun getViewModels() = tvShowFavoriteViewModel

    override fun onReadyAction() {
    }

    override fun navigateToDetailTvShow(data: DetailTvShow.Response) {
    }


}