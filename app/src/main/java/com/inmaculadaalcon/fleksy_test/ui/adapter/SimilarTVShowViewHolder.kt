package com.inmaculadaalcon.fleksy_test.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow


class SimilarTVShowViewHolder(val binding: SimilarTvShowsViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null


    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        binding.root.background = ContextCompat.getDrawable(itemView.context, tvShow.backgroundColor)
        val urlBackground = BuildConfig.IMAGE_BASE_URL+ tvShow.backdropPath
        val urlPoster = BuildConfig.IMAGE_BASE_URL + tvShow.posterPath
        binding.title.text = tvShow.name
        binding.voteAverageTextview.text = tvShow.voteAverage.toString()
        binding.overviewText.text = tvShow.overview
        binding.originalName.text = tvShow.originalName
        Glide.with(itemView.context).load(urlPoster).into(binding.imagePoster)
        Glide.with(itemView.context).load(urlBackground).transform(RoundedCorners(20)).into(binding.imageBackground) //TODO Add a placeholder
    }
}
