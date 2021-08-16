package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsInDetailViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.diffcallbacks.SimilarTVShowDiffCallback
import com.inmaculadaalcon.fleksy_test.ui.adapter.viewholders.SimilarTVShowInDetailViewHolder

class SimilarTVShowsInDetailAdapter: PagingDataAdapter<TVShow, SimilarTVShowInDetailViewHolder>(SimilarTVShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarTVShowInDetailViewHolder {
        return SimilarTVShowInDetailViewHolder(
            SimilarTvShowsInDetailViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SimilarTVShowInDetailViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}