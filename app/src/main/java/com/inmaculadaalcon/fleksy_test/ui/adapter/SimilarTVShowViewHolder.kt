package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowActivity

class SimilarTVShowViewHolder(val binding: SimilarTvShowsViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null

    init {
        binding.root.setOnClickListener{
        }
    }

    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        val url = BuildConfig.IMAGE_BASE_URL+ tvShow.backdropPath
        println("URL -> $url")
        Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+ tvShow.posterPath).into(binding.image) //TODO Add a placeholder
    }

}