package com.inmaculadaalcon.pagination.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.pagination.databinding.SimilarTvShowsInDetailViewHolderBinding
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.ui.adapter.diffcallbacks.SimilarTVShowDiffCallback
import com.inmaculadaalcon.pagination.ui.adapter.viewholders.SimilarTVShowInDetailViewHolder

class SimilarTVShowsInDetailAdapter: PagingDataAdapter<TVShow, SimilarTVShowInDetailViewHolder>(SimilarTVShowDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarTVShowInDetailViewHolder {
        val holder = SimilarTVShowInDetailViewHolder(
            SimilarTvShowsInDetailViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        holder.binding.root.setOnClickListener { view ->
            getItem(holder.adapterPosition)?.let {
                Toast.makeText(parent.context, it.name,Toast.LENGTH_SHORT ).show()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: SimilarTVShowInDetailViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}