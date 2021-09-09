package com.inmaculadaalcon.pagination.ui.adapter.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.inmaculadaalcon.pagination.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.pagination.domain.model.TVShow

class SimilarTVShowDiffCallback: DiffUtil.ItemCallback<TVShow>() {
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem == newItem
    }
}