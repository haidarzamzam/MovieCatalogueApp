package com.haidev.moviecatalogueapp.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.databinding.ItemRowMovieFavoriteBinding

class MovieFavoriteListAdapter(private val navigator: MovieFavoriteNavigator) :
    RecyclerView.Adapter<MovieFavoriteListAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailMovie.Response>()

    fun setData(list: List<DetailMovie.Response>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowMovieFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.bindItem(list[position])
        }
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemRowMovieFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailMovie.Response
        ) {
            binding.item = data
            binding.rating.rating = data.vote_average?.div(2)?.toFloat()!!
            itemView.setOnClickListener {
                navigator.navigateToDetailMovie(data)
            }
        }
    }
}