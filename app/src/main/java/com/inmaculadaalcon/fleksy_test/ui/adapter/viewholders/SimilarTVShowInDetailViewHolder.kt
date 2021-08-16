package com.inmaculadaalcon.fleksy_test.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsInDetailViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class SimilarTVShowInDetailViewHolder(val binding: SimilarTvShowsInDetailViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null

    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        val urlPoster = if (tvShow.posterPath == null) "" else BuildConfig.IMAGE_BASE_URL + tvShow.posterPath
        if (urlPoster.isEmpty()) {
            binding.posterImage.visibility = View.GONE
        } else {
            binding.posterImage.visibility = View.VISIBLE
            Glide.with(itemView.context).load(urlPoster).into(binding.posterImage)
        }
    }

}