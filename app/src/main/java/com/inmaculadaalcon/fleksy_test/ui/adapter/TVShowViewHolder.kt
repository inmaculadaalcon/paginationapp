package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowActivity
import java.util.*

class TVShowViewHolder(val binding: TopRatedTvShowItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    private val title: TextView = binding.tvshowText
    private val image: ImageView = binding.tvshowImage
    private val average: TextView = binding.voteAverageTextview
    private var tvShow: TVShow? = null

    init {
        binding.root.setOnClickListener{
            this.tvShow
            binding.root.context.startActivity(Intent(binding.root.context, DetailTVShowActivity::class.java))
        }
    }

    fun bind(tvShow: TVShow) {
        this.tvShow = tvShow
         binding.card.background =
             tvShow.backgroundColor?.let { ContextCompat.getDrawable(itemView.context, it) }
        title.text = tvShow.name ?: "loading"
        average.text = tvShow.voteAverage.toString()
        Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+tvShow.posterPath).into(image) //TODO Add a placeholder
    }

}