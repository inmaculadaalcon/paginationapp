package com.inmaculadaalcon.fleksy_test.ui.adapter.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class TVShowDiffCallback: DiffUtil.ItemCallback<TVShow>() {
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem == newItem
    }
}
