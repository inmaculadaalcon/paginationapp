package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.TopRatedTvShowItemViewBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import kotlin.properties.Delegates

class TopRatedTVShowAdapter: RecyclerView.Adapter<TopRatedTVShowAdapter.ViewHolder>() {

  var items: List<TopRatedTVShow> by Delegates.observable(emptyList()) {_, _, _ ->
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
    holder.bind(items[position].results[0].name)
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = TopRatedTvShowItemViewBinding.bind(view)

    fun bind(value: String) {
      binding.text.text = value
    }
  }

  override fun getItemCount(): Int {
    return items.size
  }
}
