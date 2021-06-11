package com.haidev.moviecatalogueapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.databinding.ItemRowMoviesProductionBinding

class DetailMovieProductionAdapter :
    RecyclerView.Adapter<DetailMovieProductionAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailMovie.Response.ProductionCompany>()

    fun setData(list: List<DetailMovie.Response.ProductionCompany>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowMoviesProductionBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowMoviesProductionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailMovie.Response.ProductionCompany
        ) {
            binding.item = data
        }
    }
}