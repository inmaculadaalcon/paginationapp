package com.inmaculadaalcon.fleksy_test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.SimilarTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TVShowsLoadStateAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.main.TopRatedTVShowsListViewModel
import com.inmaculadaalcon.fleksy_test.ui.paging.asMergedLoadStates
import kotlinx.android.synthetic.main.detail_tvshow_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailTVShowActivity: BaseActivity<DetailTvshowActivityBinding>() {

    companion object {
        const val BACKGROUND_COLOR = "backgroundcolor"
        const val TV_SHOW_ID = "tvshowId"
    }

    private val viewModel: DetailTVShowViewModel by inject()

    private val adapter = SimilarTVShowsAdapter()

    private var tvShowId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val backgroundColor = intent.getIntExtra(BACKGROUND_COLOR, R.drawable.background_blue)
        tvShowId = intent.getIntExtra(TV_SHOW_ID, 0)
        binding.root.background = ContextCompat.getDrawable(this, backgroundColor)

        collectUIState()

        binding.similarTvshows.setHasFixedSize(true)
        binding.similarTvshows.adapter = adapter.withLoadStateHeaderAndFooter(
            header = TVShowsLoadStateAdapter{ adapter.retry() },
            footer = TVShowsLoadStateAdapter{ adapter.retry() }
        )

        val linearLayoutManager = LinearLayoutManager(this)
        binding.similarTvshows.layoutManager = linearLayoutManager
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
                .collect { binding.similarTvshows.scrollToPosition(0) }
        }
        viewModel.getSimilarTVShows(tvShowId)

        lifecycleScope.launch {
            viewModel.getDetailTVShow(tvShowId)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.screenState.collect { it ->
                if (it != null){
                    drawDetails(it.data as DetailTVShow)
                }
            }
        }
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            viewModel.getSimilarTVShows(tvShowId).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> DetailTvshowActivityBinding
        get() = DetailTvshowActivityBinding::inflate

    private fun drawDetails(details: DetailTVShow) {
        binding.title.text = details.name
        binding.overviewText.text = details.overview
        Glide.with(this).load(BuildConfig.IMAGE_BASE_URL+details.backdropPath).into(binding.imagePoster)
    }

    private fun renderUI(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

        binding.similarTvshows.isVisible = !isListEmpty
        binding.similarTvshows.isVisible = isListEmpty

        // Only shows the list if refresh succeeds.
        binding.similarTvshows.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        // Show the retry state if initial load or refresh fails.
        binding.similarTvshows.isVisible = loadState.source.refresh is LoadState.Error
    }

}