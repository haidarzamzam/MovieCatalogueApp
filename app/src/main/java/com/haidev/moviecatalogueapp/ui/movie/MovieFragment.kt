package com.haidev.moviecatalogueapp.ui.movie

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.databinding.FragmentMovieBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.*
import com.haidev.moviecatalogueapp.utils.enum.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>(),
    MovieNavigator {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var skeleton: Skeleton

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding.lifecycleOwner = this
        movieViewModel.navigator = this
        skeleton = binding.rvLoading.applySkeleton(R.layout.item_row_skeleton_list, 8)
    }

    override fun setLayout() = R.layout.fragment_movie

    override fun getViewModels() = movieViewModel

    override fun onReadyAction() {
        initListMovieAdapter()
        movieViewModel.getListMovieAsync()
    }

    private fun initListMovieAdapter() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            movieListAdapter = MovieListAdapter(this@MovieFragment)
            adapter = movieListAdapter
        }
    }

    override fun onObserveAction() {
        with(movieViewModel) {
            observeFragment(dataListMovie, ::handleMovieData)
        }
    }

    private fun handleMovieData(resource: Resource<ListMovie.Response>) {
        when (resource.status) {
            Status.LOADING -> {
                showLoading(true)
            }
            Status.SUCCESS -> {
                showLoading(false)
                resource.data?.results?.let {
                    movieListAdapter.setData(it)
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
            } else {
                rvLoading.gone()
            }
        }
    }

    override fun navigateToDetailMovie(data: ListMovie.Response.Result) {
        val intent = Intent(activity?.applicationContext, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data)
        activity?.startActivity(intent)
    }
}