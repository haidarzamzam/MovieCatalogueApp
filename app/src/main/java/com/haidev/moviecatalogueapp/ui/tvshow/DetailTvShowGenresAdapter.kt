package com.haidev.moviecatalogueapp.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.databinding.ItemRowTvShowGenresBinding

class DetailTvShowGenresAdapter :
    RecyclerView.Adapter<DetailTvShowGenresAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailTvShow.Response.Genre>()

    fun setData(list: List<DetailTvShow.Response.Genre>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowTvShowGenresBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowTvShowGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailTvShow.Response.Genre
        ) {
            binding.item = data
        }
    }
}