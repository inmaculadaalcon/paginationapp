package com.inmaculadaalcon.fleksy_test.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem

class SimilarTVShowDiffCallback: DiffUtil.ItemCallback<SimilarTVShowItem>() {
    override fun areItemsTheSame(oldItem: SimilarTVShowItem, newItem: SimilarTVShowItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SimilarTVShowItem, newItem: SimilarTVShowItem): Boolean {
        return oldItem == newItem
    }
}