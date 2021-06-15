package com.haidev.moviecatalogueapp.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.databinding.ItemRowTvShowBinding

class TvShowListAdapter(
    private val listener: (ListTvShow.Response.Result) -> Unit
) : PagedListAdapter<ListTvShow.Response.Result, TvShowListAdapter.AddOnViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AddOnViewHolder(
            ItemRowTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: AddOnViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

    inner class AddOnViewHolder(
        private var binding: ItemRowTvShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ListTvShow.Response.Result) {
            binding.item = item
            binding.rating.rating = (item.vote_average?.div(2)?.toFloat() ?: 0.0) as Float
            itemView.setOnClickListener {
                listener(item)
            }
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ListTvShow.Response.Result>() {
        override fun areItemsTheSame(
            oldItem: ListTvShow.Response.Result,
            newItem: ListTvShow.Response.Result
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ListTvShow.Response.Result,
            newItem: ListTvShow.Response.Result
        ): Boolean = oldItem.id == newItem.id
    }
}