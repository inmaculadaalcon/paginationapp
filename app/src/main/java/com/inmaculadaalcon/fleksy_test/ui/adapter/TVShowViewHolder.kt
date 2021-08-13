package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowActivity

class TVShowViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.tvshow_text)
    private val image: ImageView = view.findViewById(R.id.tvshow_image)
    private val average: TextView = view.findViewById(R.id.vote_average_textview)
    private var tvShow: TVShow? = null

    init {
        view.setOnClickListener{
            view.context.startActivity(Intent(view.context, DetailTVShowActivity::class.java))
        }
    }

    fun bind(tvShow: TVShow) {
        this.tvShow = tvShow
        title.text = tvShow.name ?: "loading"
        average.text = tvShow.voteAverage.toString()
        Glide.with(itemView.context).load(BuildConfig.IMAGE_BASE_URL+tvShow.posterPath).into(image) //TODO Add a placeholder
    }

    companion object {
        fun create(parent: ViewGroup): TVShowViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.top_rated_tv_show_item_view, parent, false)
            return TVShowViewHolder(view)
        }
    }

}