package com.inmaculadaalcon.pagination.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.pagination.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.ui.adapter.diffcallbacks.SimilarTVShowDiffCallback
import com.inmaculadaalcon.pagination.ui.adapter.viewholders.SimilarTVShowViewHolder

class SimilarTVShowsAdapter: PagingDataAdapter<TVShow, SimilarTVShowViewHolder>(SimilarTVShowDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarTVShowViewHolder {
        val holder = SimilarTVShowViewHolder(
            SimilarTvShowsViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return holder
    }

    fun getItemObject(position: Int): TVShow {
        return getItem(position) as TVShow
    }

    override fun onBindViewHolder(holder: SimilarTVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}