package com.inmaculadaalcon.fleksy_test

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inmaculadaalcon.fleksy_test.databinding.ActivityBenchmarkBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.SimilarTVShowsInDetailAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BenchmarkActivity: AppCompatActivity() {
    val testExecutor = TestExecutor()
    @VisibleForTesting
    lateinit var binding: ActivityBenchmarkBinding
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenchmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = SimilarTVShowsInDetailAdapter()
        binding.list.adapter = adapter

        val config = PagingConfig(
            pageSize = 5,
            initialLoadSize = 5
        )

        val pager = Pager(config, 0) {
            MockPagingSource()
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            pager.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}


class MockPagingSource : PagingSource<Int, TVShow>() {
    private fun generateTVShow(): TVShow {
        val title = List(10) { (0..100).random() }.joinToString("")
        return TVShow("name", "", null, 123,
            "androiddev", listOf(), null, null, "",2.3,null,3.2,3,1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        val key = params.key ?: 0
        return LoadResult.Page(List(200) { generateTVShow() }.toList(), key - 1, key + 1)
    }

    // Unused in benchmark.
    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? = null
}