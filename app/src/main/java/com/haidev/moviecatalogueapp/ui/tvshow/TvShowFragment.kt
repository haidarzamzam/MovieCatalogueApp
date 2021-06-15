package com.haidev.moviecatalogueapp.ui.tvshow

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.databinding.FragmentTvShowBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.enum.Status
import com.haidev.moviecatalogueapp.utils.observeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : BaseFragment<FragmentTvShowBinding, TvShowViewModel>(),
    TvShowNavigator {
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding
    private lateinit var tvShowListAdapter: TvShowListAdapter
    private var skeleton: Skeleton? = null

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        tvShowViewModel.navigator = this
        skeleton = binding?.rvLoading?.applySkeleton(R.layout.item_row_skeleton_list, 8)
    }

    override fun setLayout() = R.layout.fragment_tv_show

    override fun getViewModels() = tvShowViewModel

    override fun onReadyAction() {
        initListMovieAdapter()
    }

    private fun initListMovieAdapter() {
        binding?.rvTvShow?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            tvShowListAdapter = TvShowListAdapter {
                navigateToDetailTvShow(it)
            }
            adapter = tvShowListAdapter
        }
    }

    override fun onObserveAction() {
        observeFragment(tvShowViewModel.getAllListTvShow()) {
            when (it.status) {
                Status.LOADING -> {
                    if (it.data.isNullOrEmpty()) skeleton?.showSkeleton()
                    else {
                        tvShowListAdapter.submitList(it.data)
                        skeleton?.showOriginal()
                    }
                }
                Status.SUCCESS -> {
                    tvShowListAdapter.submitList(it.data)
                    skeleton?.showOriginal()
                }
                Status.ERROR -> {
                    skeleton?.showOriginal()
                }
                else -> skeleton?.showOriginal()
            }
        }
    }

    override fun navigateToDetailTvShow(data: ListTvShow.Response.Result) {
        val intent = Intent(activity?.applicationContext, DetailTvShowActivity::class.java)
        intent.putExtra(DetailTvShowActivity.EXTRA_TV, data)
        activity?.startActivity(intent)
    }

}