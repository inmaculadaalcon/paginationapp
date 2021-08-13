package com.inmaculadaalcon.fleksy_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.core.right
import arrow.core.rightIfNotNull
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.TVShowsLoadStateAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.paging.asMergedLoadStates
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

import org.koin.core.component.inject

class MainActivity() : BaseActivity<ActivityMainBinding>(){

  override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
    get() = ActivityMainBinding::inflate

  private var isLoading: Boolean = false
  private var isLastPage: Boolean = false
  private val viewModel: MainViewModel by inject()

 // private val adapter = TopRatedTVShowAdapter()
  private val adapter = TopRatedTVShowsAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.recyclerview.setHasFixedSize(true)
    binding.recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
      header = TVShowsLoadStateAdapter(adapter),
      footer = TVShowsLoadStateAdapter(adapter)
    )

    val linearLayoutManager = LinearLayoutManager(this)
    binding.recyclerview.layoutManager = linearLayoutManager

   /* lifecycleScope.launchWhenStarted {
      viewModel.screenState.collectLatest {
        val data = it?.data
        if (data != null) {
         // adapter.items = data.results
          adapter.submitData(data.results)

          binding.recyclerview.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
              isLoading = true
              val nextPage = data.page + 1
              viewModel.getTopRatedTV(nextPage)
            }

            override fun getTotalPageCount(): Int = data.totalPages

            override fun isLastPage(): Boolean = isLastPage

            override fun isLoading(): Boolean = isLoading

          })
        }
      }
    }*/
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
    viewModel.getTopRatedTV(1)

   /* binding.recyclerview.addOnScrollListener(PaginationScrollListener(linearLayoutManager){

    })*/
  }

}
