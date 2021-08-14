package com.inmaculadaalcon.fleksy_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.ui.adapter.TVShowsLoadStateAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.paging.asMergedLoadStates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

import org.koin.core.component.inject

class MainActivity() : BaseActivity<ActivityMainBinding>(){

  override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
    get() = ActivityMainBinding::inflate

  private val viewModel: TopRatedTVShowsListViewModel by inject()

  private val adapter = TopRatedTVShowsAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    collectUIState()

    binding.recyclerview.setHasFixedSize(true)
    binding.recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
      header = TVShowsLoadStateAdapter{ adapter.retry() },
      footer = TVShowsLoadStateAdapter{ adapter.retry() }
    )

    val linearLayoutManager = LinearLayoutManager(this)
    binding.recyclerview.layoutManager = linearLayoutManager

    adapter.addLoadStateListener { loadState -> renderUI(loadState) }

    lifecycleScope.launchWhenCreated {
      adapter.loadStateFlow
        // Use a state-machine to track LoadStates such that we only transition to
        // NotLoading from a RemoteMediator load if it was also presented to UI.
        .asMergedLoadStates()
        // Only emit when REFRESH changes, as we only want to react on loads replacing the
        // list.
        .distinctUntilChangedBy { it.refresh }
        // Only react to cases where REFRESH completes i.e., NotLoading.
        .filter { it.refresh is LoadState.NotLoading }
        // Scroll to top is synchronous with UI updates, even if remote load was triggered.
        .collect { binding.recyclerview.scrollToPosition(0) }
    }
    viewModel.getTopRatedTVShows()
  }

    private fun collectUIState() {
        lifecycleScope.launch {
            viewModel.getTopRatedTVShows().collectLatest { tvshows ->
                adapter.submitData(tvshows)
            }
        }
    }

    private fun renderUI(loadState: CombinedLoadStates) {
    val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

    binding.recyclerview.isVisible = !isListEmpty
    binding.tvshowEmpty.isVisible = isListEmpty

    // Only shows the list if refresh succeeds.
    binding.recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
    // Show loading spinner during initial load or refresh.
    binding.progressbarTvshows.isVisible = loadState.source.refresh is LoadState.Loading
    // Show the retry state if initial load or refresh fails.
    binding.btnMoviesRetry.isVisible = loadState.source.refresh is LoadState.Error
  }

}
