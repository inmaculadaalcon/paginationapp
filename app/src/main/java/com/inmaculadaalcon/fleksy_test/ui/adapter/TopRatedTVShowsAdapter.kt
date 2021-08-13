package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class TopRatedTVShowsAdapter: PagingDataAdapter<TVShow, TVShowViewHolder>(POST_COMPARATOR) {

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }

    override fun onBindViewHolder(
        holder: TVShowViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder.create(parent)
    }

    companion object {
        private val PAYLOAD_SCORE = Any()
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
                oldItem.name == newItem.name

            override fun getChangePayload(oldItem: TVShow, newItem: TVShow): Any? {
                    PAYLOAD_SCORE
             }
        }

    }
}