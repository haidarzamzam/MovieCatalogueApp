package com.haidev.moviecatalogueapp.ui.favorite.movie

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.databinding.FragmentMovieFavoriteBinding
import com.haidev.moviecatalogueapp.ui.base.BaseFragment
import com.haidev.moviecatalogueapp.ui.movie.DetailMovieActivity
import com.haidev.moviecatalogueapp.utils.gone
import com.haidev.moviecatalogueapp.utils.observeFragment
import com.haidev.moviecatalogueapp.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : BaseFragment<FragmentMovieFavoriteBinding, MovieFavoriteViewModel>(),
    MovieFavoriteNavigator {

    private val movieFavoriteViewModel: MovieFavoriteViewModel by viewModel()
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var movieFavoriteListAdapter: MovieFavoriteListAdapter
    private var skeleton: Skeleton? = null

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        movieFavoriteViewModel.navigator = this
        skeleton = binding?.rvLoading?.applySkeleton(R.layout.item_row_skeleton_list, 8)
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
                if (it.isEmpty()) {
                    binding?.tvNoData?.visible()
                } else {
                    binding?.tvNoData?.gone()
                }
                movieFavoriteListAdapter.setData(it as List<DetailMovie.Response>)
            }
            binding?.executePendingBindings()
        }
    }

    private fun initListMovieAdapter() {
        binding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            movieFavoriteListAdapter = MovieFavoriteListAdapter(this@MovieFavoriteFragment)
            adapter = movieFavoriteListAdapter
        }
    }

    override fun navigateToDetailMovie(data: DetailMovie.Response) {
        val intent = Intent(activity?.applicationContext, DetailMovieActivity::class.java)
        val listGenres = arrayListOf<Int>()
        data.genres?.forEach { it.id?.let { it1 -> listGenres.add(it1) } }
        val movie = data.id?.let {
            ListMovie.Response.Result(
                data.adult,
                data.backdrop_path,
                listGenres,
                it,
                data.original_language,
                data.original_title,
                data.overview,
                data.popularity,
                data.poster_path,
                data.release_date,
                data.title,
                data.video,
                data.vote_average,
                data.vote_count
            )
        }
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
        activity?.startActivity(intent)
    }
}