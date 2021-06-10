package com.haidev.moviecatalogueapp.ui.tvshow

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.databinding.FragmentTvShowBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.enum.Status
import com.haidev.moviecatalogueapp.utils.gone
import com.haidev.moviecatalogueapp.utils.observe
import com.haidev.moviecatalogueapp.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : BaseFragment<FragmentTvShowBinding, TvShowViewModel>(),
    TvShowNavigator {
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowListAdapter: TvShowListAdapter
    private lateinit var skeleton: Skeleton

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        tvShowViewModel.navigator = this
        skeleton = binding.rvLoading.applySkeleton(R.layout.item_row_list_skeleton, 8)
    }

    override fun setLayout() = R.layout.fragment_tv_show

    override fun getViewModels() = tvShowViewModel

    override fun onReadyAction() {
        initListMovieAdapter()
        tvShowViewModel.getListTvShowAsync()
    }

    private fun initListMovieAdapter() {
        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            tvShowListAdapter = TvShowListAdapter(this@TvShowFragment)
            adapter = tvShowListAdapter
        }
    }

    override fun onObserveAction() {
        with(tvShowViewModel) {
            observe(dataListTvShow, ::handleTvShowData)
        }
    }

    private fun handleTvShowData(resource: Resource<ListTvShow.Response>) {
        when (resource.status) {
            Status.LOADING -> {
                showLoading(true)
            }
            Status.SUCCESS -> {
                showLoading(false)
                resource.data?.results?.let {
                    tvShowListAdapter.setData(it)
                }
                binding.executePendingBindings()
            }
            else -> {
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        skeleton.showSkeleton()
        with(binding) {
            if (isLoading) {
                rvLoading.visible()
                groupContent.gone()
            } else {
                rvLoading.gone()
                groupContent.visible()
            }
        }
    }

    override fun navigateToDetailTvShow(data: ListTvShow.Response.Result) {
        val intent = Intent(activity?.applicationContext, DetailTvShowActivity::class.java)
        activity?.startActivity(intent)
    }

}