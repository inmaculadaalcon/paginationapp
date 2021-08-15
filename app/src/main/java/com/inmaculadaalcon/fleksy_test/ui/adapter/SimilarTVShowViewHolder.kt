package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearSmoothScroller
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
        binding.root.setOnClickListener {
            val rv = binding.root.parent as RecyclerView
            rv.smoothScrollToCenteredPosition(position)
        }
    }

    fun RecyclerView.smoothScrollToCenteredPosition(position: Int) {
        val smoothScroller = object : LinearSmoothScroller(context) {

            override fun calculateDxToMakeVisible(view: View?,
                                                  snapPref: Int): Int {
                val dxToStart = super.calculateDxToMakeVisible(view, SNAP_TO_START)
                val dxToEnd = super.calculateDxToMakeVisible(view, SNAP_TO_END)

                return (dxToStart + dxToEnd) / 2
            }
        }

        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }

    fun bind(tvShow: TVShow) {
        this.similarTVShowItem = tvShow
        val url = BuildConfig.IMAGE_BASE_URL+ tvShow.backdropPath
        println("URl -> $url")
        Glide.with(itemView.context).load(url).into(binding.imagePoster) //TODO Add a placeholder
    }

}