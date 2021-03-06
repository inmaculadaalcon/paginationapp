package com.inmaculadaalcon.pagination.ui.adapter.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.inmaculadaalcon.pagination.BuildConfig
import com.inmaculadaalcon.pagination.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.pagination.domain.model.TVShow


class SimilarTVShowViewHolder(val binding: SimilarTvShowsViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null


    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        val urlBackground = if (tvShow.backdropPath == null) "" else  BuildConfig.IMAGE_BASE_URL+ tvShow.backdropPath
        val urlPoster = if (tvShow.posterPath == null) "" else BuildConfig.IMAGE_BASE_URL + tvShow.posterPath
        binding.title.text = tvShow.name
        binding.voteAverageTextview.text = tvShow.voteAverage.toString()
        binding.overviewText.text = tvShow.overview
        binding.originalName.text = tvShow.originalName
        if (urlPoster.isEmpty()) {
            binding.imagePoster.visibility = View.GONE
        } else {
            binding.imagePoster.visibility = View.VISIBLE
            Glide.with(itemView.context).load(urlPoster).into(binding.imagePoster)
        }
        if (urlBackground.isEmpty()) {
            binding.imageBackground.visibility = View.GONE
        } else {
            binding.imageBackground.visibility = View.VISIBLE
            Glide.with(itemView.context).load(urlBackground).transform(RoundedCorners(20)).into(binding.imageBackground) //TODO Add a placeholder
        }
    }
}
