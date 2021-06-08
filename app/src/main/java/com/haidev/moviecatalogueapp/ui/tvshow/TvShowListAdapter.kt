package com.haidev.moviecatalogueapp.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.databinding.ItemRowTvShowBinding

class TvShowListAdapter(private val navigator: TvShowNavigator) :
    RecyclerView.Adapter<TvShowListAdapter.ViewHolder>() {

    private var list = mutableListOf<ListTvShow.Response.Result>()

    fun setData(list: List<ListTvShow.Response.Result>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRowTvShowBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemRowTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: ListTvShow.Response.Result
        ) {
            binding.item = data
            itemView.setOnClickListener {
                navigator.navigateToDetailTvShow(data)
            }
        }
    }
}