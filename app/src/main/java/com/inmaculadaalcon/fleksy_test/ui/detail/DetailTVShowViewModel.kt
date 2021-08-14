package com.inmaculadaalcon.fleksy_test.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.ui.base.TVShowDetailScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailTVShowViewModel(private val tvShowsRepository: TVShowsRepository): ViewModel() {
    val screenStateQueue = MutableStateFlow<TVShowDetailScreenState.SuccessTopRatedTVShows?>(null)
    val screenState = screenStateQueue.asStateFlow()

    fun getDetailTVShow(tvShowId: Int){
        viewModelScope.launch {
            tvShowsRepository.getDetailTVShow(tvShowId).collect {
                println("It -> $it")
                val detailScreenState = TVShowDetailScreenState.SuccessTopRatedTVShows(it as DetailTVShow)
                screenStateQueue.value =  detailScreenState
            }
        }
    }

    fun getSimilarTVShows(tvShowId: Int): Flow<PagingData<SimilarTVShowItem>> {
        return tvShowsRepository.getSimilarTVShows(tvShowId).map {
            pagingData ->
            pagingData.map {
                it
            }
        }.cachedIn(viewModelScope)
    }

}