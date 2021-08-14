package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowActivity

class TopRatedTVShowsAdapter: PagingDataAdapter<TVShow, TVShowViewHolder>(TVShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val holder = TVShowViewHolder(
            TopRatedTvShowItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener { view ->
            getItem(holder.adapterPosition)?.let { tvShow ->
                val intent = Intent(view.context, DetailTVShowActivity::class.java)
                intent.putExtra(DetailTVShowActivity.BACKGROUND_COLOR, tvShow.backgroundColor)
                intent.putExtra(DetailTVShowActivity.TV_SHOW_ID, tvShow.id)
                startActivity(view.context, intent, null)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}