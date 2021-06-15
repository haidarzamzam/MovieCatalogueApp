package com.haidev.moviecatalogueapp.ui.favorite.movie

import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.databinding.FragmentMovieFavoriteBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.utils.observeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : BaseFragment<FragmentMovieFavoriteBinding, MovieFavoriteViewModel>(),
    MovieFavoriteNavigator {

    private val movieFavoriteViewModel: MovieFavoriteViewModel by viewModel()
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieFavoriteListAdapter: MovieFavoriteListAdapter
    private lateinit var skeleton: Skeleton

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding.lifecycleOwner = this
        movieFavoriteViewModel.navigator = this
        skeleton = binding.rvLoading.applySkeleton(R.layout.item_row_skeleton_list, 8)
    }

    override fun setLayout() = R.layout.fragment_movie_favorite

    override fun getViewModels() = movieFavoriteViewModel

    override fun onReadyAction() {
        initListMovieAdapter()
        movieFavoriteViewModel.getAllFavoriteMovie()
    }

    override fun onObserveAction() {
        observeFragment(movieFavoriteViewModel.getAllFavoriteMovie()) {
            it.let {
                movieFavoriteListAdapter.setData(it as List<DetailMovie.Response>)
            }
            binding.executePendingBindings()
        }
    }

    private fun initListMovieAdapter() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            movieFavoriteListAdapter = MovieFavoriteListAdapter(this@MovieFavoriteFragment)
            adapter = movieFavoriteListAdapter
        }
    }

    override fun navigateToDetailMovie(data: DetailMovie.Response) {

    }
}