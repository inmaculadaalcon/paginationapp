package com.inmaculadaalcon.fleksy_test.ui.detail

import ProminentLayoutManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.SimilarTVShowsAdapter
import com.inmaculadaalcon.fleksy_test.ui.adapter.SimilarTVShowsInDetailAdapter
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
    private val adapterSimilarInDetail = SimilarTVShowsInDetailAdapter()
    val snapHelper = PagerSnapHelper()

    private var tvShowId: Int = 0
    private var tvShow: TVShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvShowId = intent.getIntExtra(TV_SHOW_ID, 0)
        val jsonTVShow = intent.getStringExtra(TV_SHOW)
        tvShow = moshi.adapter(TVShow::class.java).fromJson(jsonTVShow!!)
        binding.root.background = tvShow?.backgroundColor?.let {
            ContextCompat.getDrawable(this,
                it
            )
        }
        collectUIState()

        binding.back.setOnClickListener {
            finish()
        }

        binding.tvShowsRecyclerview.setHasFixedSize(true)
        binding.tvShowsRecyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
            header = TVShowsLoadStateAdapter { adapter.retry() },
            footer = TVShowsLoadStateAdapter { adapter.retry() }
        )

        with(binding.tvShowsRecyclerview) {
            setItemViewCacheSize(4)
            binding.tvShowsRecyclerview.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            snapHelper.attachToRecyclerView(this)
        }

        binding.similarRecyclerview.setHasFixedSize(true)
        binding.similarRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.similarRecyclerview.adapter = adapterSimilarInDetail


        binding.tvShowsRecyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState === RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int = (binding.tvShowsRecyclerview.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val tvShowSelected = getItemObject(position)
                    binding.root.background =  tvShowSelected.backgroundColor?.let {
                        ContextCompat.getDrawable(recyclerView.context,
                            it
                        )
                    }
                    lifecycleScope.launch {
                        tvShowSelected.let {
                            viewModel.getSimilarTVShows(tvShowSelected.id, it).collectLatest {
                                binding.similarRecyclerview.scrollToPosition(0)
                                adapterSimilarInDetail.submitData(it)
                            }
                        }
                    }
                }
            }
        })

        lifecycleScope.launch {
            tvShow?.let {
                viewModel.getSimilarTVShows(tvShow!!.id, tvShow!!).collectLatest {
                    adapterSimilarInDetail.submitData(it)
                }
            }
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
             //   if (it != null){
                   // drawDetails(it.data) -> This method was calling when I tried to make this with the detail service
              //  }
            }
        }
    }

    private fun getItemObject(position: Int): TVShow {
        //Call to get the similar tvshows
        return adapter.getItemObject(position)
    }

    private fun collectUIState() {
        lifecycleScope.launch {
            tvShow?.let {
                viewModel.getSimilarTVShows(tvShowId, it).collectLatest { it ->
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
        binding.tvShowsRecyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
    }

}