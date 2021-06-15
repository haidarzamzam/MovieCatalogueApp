package com.haidev.moviecatalogueapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.databinding.ItemRowMovieBinding

class MovieListAdapter(
    private val listener: (ListMovie.Response.Result) -> Unit
) : PagedListAdapter<ListMovie.Response.Result, MovieListAdapter.AddOnViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AddOnViewHolder(
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: AddOnViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

    inner class AddOnViewHolder(
        private var binding: ItemRowMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ListMovie.Response.Result) {
            binding.item = item
            binding.rating.rating = item.vote_average.div(2).toFloat()
            itemView.setOnClickListener {
                listener(item)
            }
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ListMovie.Response.Result>() {
        override fun areItemsTheSame(
            oldItem: ListMovie.Response.Result,
            newItem: ListMovie.Response.Result
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ListMovie.Response.Result,
            newItem: ListMovie.Response.Result
        ): Boolean = oldItem.id == newItem.id
    }
}