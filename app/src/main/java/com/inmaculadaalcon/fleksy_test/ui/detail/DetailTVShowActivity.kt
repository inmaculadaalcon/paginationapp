package com.inmaculadaalcon.fleksy_test.ui.detail

import ProminentLayoutManager
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
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
        val backgroundColor = intent.getIntExtra(BACKGROUND_COLOR, R.drawable.background_blue)
        tvShowId = intent.getIntExtra(TV_SHOW_ID, 0)
        binding.root.background = ContextCompat.getDrawable(this, backgroundColor)
        val jsonTVShow = intent.getStringExtra(TV_SHOW)
        val tvShow = moshi.adapter(TVShow::class.java).fromJson(jsonTVShow!!)
        collectUIState()

        binding.tvShowsRecyclerview.setHasFixedSize(true)
        binding.tvShowsRecyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
            header = TVShowsLoadStateAdapter{ adapter.retry() },
            footer = TVShowsLoadStateAdapter{ adapter.retry() }
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
        viewModel.getSimilarTVShows(tvShowId, currentTVShow = tvShow)

        lifecycleScope.launch {
            viewModel.getDetailTVShow(tvShowId)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.screenState.collect { it ->
                if (it != null){
                    drawDetails(it.data)
                }
            }
        }
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            viewModel.getSimilarTVShows(tvShowId, currentTVShow = tvShow).collectLatest {
                it ->
                println("It -> $it.")
                adapter.submitData(it)
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> DetailTvshowActivityBinding
        get() = DetailTvshowActivityBinding::inflate

    private fun drawDetails(details: DetailTVShow) {
        //binding.title.text = details.name
        //binding.overviewText.text = details.overview
       // Glide.with(this).load(BuildConfig.IMAGE_BASE_URL+details.backdropPath).into(binding.imagePoster)
    }

    private fun renderUI(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

        binding.tvShowsRecyclerview.isVisible = !isListEmpty

        // Only shows the list if refresh succeeds.
        binding.tvShowsRecyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        // Show the retry state if initial load or refresh fails.
       // binding.similarTvshows.isVisible = loadState.source.refresh is LoadState.Error
    }


    /** Works best with a [LinearLayoutManager] in [LinearLayoutManager.HORIZONTAL] orientation */
    class LinearHorizontalSpacingDecoration(@Px private val innerSpacing: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val itemPosition = parent.getChildAdapterPosition(view)

            outRect.left = if (itemPosition == 0) 0 else innerSpacing / 2
            outRect.right = if (itemPosition == state.itemCount - 1) 0 else innerSpacing / 2
        }
    }
    /** Offset the first and last items to center them */
    class BoundsOffsetDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val itemPosition = parent.getChildAdapterPosition(view)

            // It is crucial to refer to layoutParams.width (view.width is 0 at this time)!
            val itemWidth = view.layoutParams.width
            val offset = (parent.width - itemWidth) / 2

            if (itemPosition == 0) {
                outRect.left = offset
            } else if (itemPosition == state.itemCount - 1) {
                outRect.right = offset
            }
        }
    }

}