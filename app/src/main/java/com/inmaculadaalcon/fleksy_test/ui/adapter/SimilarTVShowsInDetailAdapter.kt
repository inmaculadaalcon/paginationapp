package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsInDetailViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.diffcallbacks.SimilarTVShowDiffCallback
import com.inmaculadaalcon.fleksy_test.ui.adapter.viewholders.SimilarTVShowInDetailViewHolder

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