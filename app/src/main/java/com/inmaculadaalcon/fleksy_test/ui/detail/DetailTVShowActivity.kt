package com.inmaculadaalcon.fleksy_test.ui.detail

import ProminentLayoutManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.PagerSnapHelper
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.SimilarTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TVShowsLoadStateAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.paging.asMergedLoadStates
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailTVShowActivity: BaseActivity<DetailTvshowActivityBinding>(), KoinComponent {

    companion object {
        const val BACKGROUND_COLOR = "backgroundcolor"
        const val TV_SHOW_ID = "tvshowId"
        const val TV_SHOW = "tvshow"
    }

    private val viewModel: DetailTVShowViewModel by inject()
    private val moshi: Moshi by inject()

    private val adapter = SimilarTVShowsAdapter()
    val snapHelper = PagerSnapHelper()

    private var tvShowId: Int = 0
    private var tvShow: TVShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvShowId = intent.getIntExtra(TV_SHOW_ID, 0)
        val jsonTVShow = intent.getStringExtra(TV_SHOW)
        tvShow = moshi.adapter(TVShow::class.java).fromJson(jsonTVShow!!)

        collectUIState()

        binding.tvShowsRecyclerview.setHasFixedSize(true)
        binding.tvShowsRecyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
            header = TVShowsLoadStateAdapter { adapter.retry() },
            footer = TVShowsLoadStateAdapter { adapter.retry() }
        )

        with(binding.tvShowsRecyclerview) {
            setItemViewCacheSize(4)
            binding.tvShowsRecyclerview.layoutManager = ProminentLayoutManager(this.context)
            snapHelper.attachToRecyclerView(this)
        }

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
                .collect { binding.tvShowsRecyclerview.scrollToPosition(0) }
        }

        if (tvShow != null) {
            viewModel.getSimilarTVShows(tvShowId= tvShowId, tvShow!!)
        }

        lifecycleScope.launch {
            viewModel.getDetailTVShow(tvShowId)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.screenState.collect { it ->
                if (it != null){
                   // drawDetails(it.data) -> This method was calling when I tried to make this with the detail service
                }
            }
        }
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            tvShow?.let {
                viewModel.getSimilarTVShows(tvShowId, it).collectLatest { it ->
                    println("It -> $it.")
                    adapter.submitData(it)
                }
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> DetailTvshowActivityBinding
        get() = DetailTvshowActivityBinding::inflate


    private fun renderUI(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

        binding.tvShowsRecyclerview.isVisible = !isListEmpty

        // Only shows the list if refresh succeeds.
        binding.tvShowsRecyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        // Show the retry state if initial load or refresh fails.
       // binding.similarTvshows.isVisible = loadState.source.refresh is LoadState.Error
    }

}