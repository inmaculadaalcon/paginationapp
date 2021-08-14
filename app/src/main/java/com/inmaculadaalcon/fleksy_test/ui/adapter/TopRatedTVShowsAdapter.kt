package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class TopRatedTVShowsAdapter: PagingDataAdapter<TVShow, TVShowViewHolder>(TVShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val holder = TVShowViewHolder(
            TopRatedTvShowItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener { view ->
            getItem(holder.adapterPosition)?.let { tvShow ->

            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}