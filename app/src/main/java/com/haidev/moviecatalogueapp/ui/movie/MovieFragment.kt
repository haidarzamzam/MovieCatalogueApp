package com.haidev.moviecatalogueapp.ui.movie

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.databinding.FragmentMovieBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.enum.Status
import com.haidev.moviecatalogueapp.utils.observeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>(),
    MovieNavigator {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding
    private lateinit var movieListAdapter: MovieListAdapter
    private var skeleton: Skeleton? = null

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        movieViewModel.navigator = this
        skeleton = binding?.rvLoading?.applySkeleton(R.layout.item_row_skeleton_list, 8)
    }

    override fun setLayout() = R.layout.fragment_movie

    override fun getViewModels() = movieViewModel

    override fun onReadyAction() {
        initListMovieAdapter()
    }

    private fun initListMovieAdapter() {
        binding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            movieListAdapter = MovieListAdapter {
                navigateToDetailMovie(it)
            }
            adapter = movieListAdapter
        }
    }


    override fun onObserveAction() {
        observeFragment(movieViewModel.getAllListMovie()) {
            when (it.status) {
                Status.LOADING -> {
                    if (it.data.isNullOrEmpty()) skeleton?.showSkeleton()
                    else {
                        movieListAdapter.submitList(it.data)
                        skeleton?.showOriginal()
                    }
                }
                Status.SUCCESS -> {
                    movieListAdapter.submitList(it.data)
                    skeleton?.showOriginal()
                }
                Status.ERROR -> {
                    skeleton?.showOriginal()
                }
                else -> skeleton?.showOriginal()
            }
        }
    }

    override fun navigateToDetailMovie(data: ListMovie.Response.Result) {
        val intent = Intent(activity?.applicationContext, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data)
        activity?.startActivity(intent)
    }
}