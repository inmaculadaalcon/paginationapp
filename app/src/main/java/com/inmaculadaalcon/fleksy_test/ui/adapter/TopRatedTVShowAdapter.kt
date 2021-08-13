package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import arrow.core.extensions.list.align.empty
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import java.util.*
import kotlin.properties.Delegates

class TopRatedTVShowAdapter: RecyclerView.Adapter<TopRatedTVShowAdapter.ViewHolder>() {

  var items: List<TVShow> by Delegates.observable(emptyList()) { _, old, new ->
    notifyDataSetChanged()
  }



  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.top_rated_tv_show_item_view, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = TopRatedTvShowItemViewBinding.bind(view)
    private val backgrounds = listOf(R.drawable.background, R.drawable.background_blue, R.drawable.background_green, R.drawable.background_orange, R.drawable.background_pink, R.drawable.background_purple)

    fun bind(tvShow: TVShow) {
      val background = backgrounds[Random().nextInt(backgrounds.size)]
      binding.root.background = ContextCompat.getDrawable(itemView.context, background)
      Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+tvShow.posterPath).into(binding.tvshowImage)
      binding.tvshowText.text = tvShow.name
      binding.voteAverageTextview.text = tvShow.voteAverage.toString()
    }
  }

  override fun getItemCount(): Int {
    return items.size
  }
}
