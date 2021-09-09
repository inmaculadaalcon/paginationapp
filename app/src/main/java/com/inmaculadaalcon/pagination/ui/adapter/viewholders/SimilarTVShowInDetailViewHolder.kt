package com.inmaculadaalcon.pagination.ui.adapter.viewholders

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.pagination.BuildConfig
import com.inmaculadaalcon.pagination.R
import com.inmaculadaalcon.pagination.databinding.SimilarTvShowsInDetailViewHolderBinding
import com.inmaculadaalcon.pagination.domain.model.TVShow

class SimilarTVShowInDetailViewHolder(val binding: SimilarTvShowsInDetailViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null

    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        val urlPoster = if (tvShow.posterPath == null) "" else BuildConfig.IMAGE_BASE_URL + tvShow.posterPath
        if (urlPoster.isEmpty()) {
            binding.posterImage.visibility = View.GONE
        } else {
            binding.posterImage.visibility = View.VISIBLE
            Glide.with(itemView.context).load(urlPoster).placeholder(R.drawable.ic_launcher_background).into(binding.posterImage)
        }
    }

}