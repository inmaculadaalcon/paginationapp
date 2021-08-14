package com.inmaculadaalcon.fleksy_test.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class TVShowsLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<TVShowLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: TVShowLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): TVShowLoadStateViewHolder {
        return TVShowLoadStateViewHolder.create(parent, retry)
    }

}