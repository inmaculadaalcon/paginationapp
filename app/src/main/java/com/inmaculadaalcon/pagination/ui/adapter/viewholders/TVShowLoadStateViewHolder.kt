package com.inmaculadaalcon.pagination.ui.adapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.inmaculadaalcon.pagination.R
import com.inmaculadaalcon.pagination.databinding.TvshowsLoadStateItemFooterBinding

class TVShowLoadStateViewHolder(private val binding: TvshowsLoadStateItemFooterBinding, retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnTvshowRetry.setOnClickListener{ retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvShowErrorDescription.text = loadState.error.localizedMessage
        }
        binding.progressBarLoadMore.isVisible = loadState is LoadState.Loading
        binding.btnTvshowRetry.isVisible = loadState is LoadState.Error
        binding.tvShowErrorDescription.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): TVShowLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tvshows_load_state_item_footer, parent, false)
            val binding = TvshowsLoadStateItemFooterBinding.bind(view)
            return TVShowLoadStateViewHolder(binding, retry)
        }
    }
}