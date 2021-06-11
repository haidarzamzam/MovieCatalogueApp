package com.haidev.moviecatalogueapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.databinding.ItemRowMoviesGenresBinding

class DetailMovieGenresAdapter :
    RecyclerView.Adapter<DetailMovieGenresAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailMovie.Response.Genre>()

    fun setData(list: List<DetailMovie.Response.Genre>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowMoviesGenresBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowMoviesGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailMovie.Response.Genre
        ) {
            binding.item = data
        }
    }
}