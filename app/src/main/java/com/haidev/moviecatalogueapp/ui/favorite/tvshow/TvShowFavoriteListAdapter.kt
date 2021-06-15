package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.databinding.ItemRowTvShowFavoriteBinding

class TvShowFavoriteListAdapter(private val navigator: TvShowFavoriteNavigator) :
    RecyclerView.Adapter<TvShowFavoriteListAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailTvShow.Response>()

    fun setData(list: List<DetailTvShow.Response>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowTvShowFavoriteBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowTvShowFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailTvShow.Response
        ) {
            binding.item = data
            binding.rating.rating = data.vote_average?.div(2)?.toFloat()!!
            itemView.setOnClickListener {
                navigator.navigateToDetailTvShow(data)
            }
        }
    }
}