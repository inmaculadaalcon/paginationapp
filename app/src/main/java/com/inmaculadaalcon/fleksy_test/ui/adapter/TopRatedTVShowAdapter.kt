package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlin.properties.Delegates

class TopRatedTVShowAdapter: RecyclerView.Adapter<TopRatedTVShowAdapter.ViewHolder>() {

  var items: List<TVShow> by Delegates.observable(emptyList()) {_, _, _ ->
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TopRatedTVShowAdapter.ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.top_rated_tv_show_item_view, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = TopRatedTvShowItemViewBinding.bind(view)

    fun bind(tvShow: TVShow) {
      Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+tvShow.posterPath).into(binding.tvshowImage)
      binding.tvshowText.text = tvShow.name
    }
  }

  override fun getItemCount(): Int {
    return items.size
  }
}
