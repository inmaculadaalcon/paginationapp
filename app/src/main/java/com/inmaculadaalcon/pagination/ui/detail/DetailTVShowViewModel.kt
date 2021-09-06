package com.inmaculadaalcon.pagination.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.inmaculadaalcon.pagination.data.repository.TVShowsRepository
import com.inmaculadaalcon.pagination.domain.model.DetailTVShow
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.ui.base.TVShowDetailScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailTVShowViewModel(private val tvShowsRepository: TVShowsRepository): ViewModel() {
    val screenStateQueue = MutableStateFlow<TVShowDetailScreenState.SuccessTopRatedTVShows?>(null)
    val screenState = screenStateQueue.asStateFlow()

    fun getDetailTVShow(tvShowId: Int){
        viewModelScope.launch {
            tvShowsRepository.getDetailTVShow(tvShowId).collect {
                val detailScreenState = TVShowDetailScreenState.SuccessTopRatedTVShows(it as DetailTVShow)
                screenStateQueue.value =  detailScreenState
            }
        }
    }

    fun getSimilarTVShows(tvShowId: Int, currentTVShow: TVShow): Flow<PagingData<TVShow>> {
        return tvShowsRepository.getSimilarTVShows(tvShowId, currentTVShow).map {
            pagingData ->
            pagingData.map {
                 it
            }
        }.cachedIn(viewModelScope)
    }

}