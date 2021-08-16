package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.diffcallbacks.TVShowDiffCallback
import com.inmaculadaalcon.fleksy_test.ui.adapter.viewholders.TVShowViewHolder
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowActivity
import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TopRatedTVShowsAdapter: PagingDataAdapter<TVShow, TVShowViewHolder>(TVShowDiffCallback()), KoinComponent {

    private val moshi: Moshi by inject()

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

                val jsonTVShow = moshi.adapter<TVShow>(TVShow::class.java).toJson(tvShow)
                intent.putExtra(DetailTVShowActivity.TV_SHOW, jsonTVShow)
                startActivity(view.context, intent, null)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }
}