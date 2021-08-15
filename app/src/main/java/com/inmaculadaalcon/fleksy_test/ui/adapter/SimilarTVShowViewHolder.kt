package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.databinding.SimilarTvShowsViewHolderBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow

class SimilarTVShowViewHolder(val binding: SimilarTvShowsViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var similarTVShowItem: TVShow? = null

    init {
        binding.root.setOnClickListener {
            val rv = binding.root.parent as RecyclerView
            rv.smoothScrollToCenteredPosition(position)
        }
    }

    private fun RecyclerView.smoothScrollToCenteredPosition(position: Int) {
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
        binding.root.background = ContextCompat.getDrawable(itemView.context, tvShow.backgroundColor)
        val url = BuildConfig.IMAGE_BASE_URL+ tvShow.backdropPath
        binding.title.text = tvShow.name
        binding.overviewText.text = tvShow.overview
        Glide.with(itemView.context).load(url).into(binding.imagePoster) //TODO Add a placeholder
    }

}