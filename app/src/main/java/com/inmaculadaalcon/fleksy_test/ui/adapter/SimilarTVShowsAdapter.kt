package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class SimilarTVShowsAdapter: PagingDataAdapter<TVShow, SimilarTVShowViewHolder>(SimilarTVShowDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarTVShowViewHolder {
        val holder = SimilarTVShowViewHolder(
            SimilarTvShowsViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return holder
    }

    override fun onBindViewHolder(holder: SimilarTVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}