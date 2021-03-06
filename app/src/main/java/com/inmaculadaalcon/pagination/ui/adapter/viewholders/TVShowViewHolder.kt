package com.inmaculadaalcon.pagination.ui.adapter.viewholders

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.pagination.BuildConfig
import com.inmaculadaalcon.pagination.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.ui.detail.DetailTVShowActivity
import kotlinx.android.synthetic.main.top_rated_tv_show_item_view.view.*

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
        binding.card.background = ContextCompat.getDrawable(itemView.context, tvShow.backgroundColor)
        title.text = tvShow.name ?: "loading"
        average.text = tvShow.voteAverage.toString()
        binding.tvshowOriginalnameText.text = tvShow.originalName
        Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+tvShow.posterPath).into(image) //TODO Add a placeholder
    }

}