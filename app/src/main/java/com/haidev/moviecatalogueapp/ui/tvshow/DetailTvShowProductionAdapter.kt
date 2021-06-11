package com.haidev.moviecatalogueapp.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.databinding.ItemRowTvShowProductionBinding

class DetailTvShowProductionAdapter :
    RecyclerView.Adapter<DetailTvShowProductionAdapter.ViewHolder>() {

    private var list = mutableListOf<DetailTvShow.Response.ProductionCompany>()

    fun setData(list: List<DetailTvShow.Response.ProductionCompany>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowTvShowProductionBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowTvShowProductionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: DetailTvShow.Response.ProductionCompany
        ) {
            binding.item = data
        }
    }
}