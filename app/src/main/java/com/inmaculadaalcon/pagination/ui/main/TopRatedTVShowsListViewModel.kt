package com.inmaculadaalcon.pagination.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.inmaculadaalcon.pagination.data.repository.TVShowsRepository
import com.inmaculadaalcon.pagination.domain.model.Configuration
import com.inmaculadaalcon.pagination.domain.model.DetailTVShow
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.ui.base.TVShowDetailScreenState
import com.inmaculadaalcon.pagination.ui.base.TVShowsConfigurationState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TopRatedTVShowsListViewModel(private val tvShowsRepository: TVShowsRepository): ViewModel() {
    val screenStateQueue = MutableStateFlow<TVShowsConfigurationState.SuccessConfiguration?>(null)
    val screenState = screenStateQueue.asStateFlow()

    fun getTopRatedTVShows(): Flow<PagingData<TVShow>> {
        return tvShowsRepository.getTopRatedTVShows().map {
            pagingData ->
                pagingData.map {
                    it
                }
        }.cachedIn(viewModelScope)
    }

    fun getConfiguration(){
        viewModelScope.launch {
            tvShowsRepository.getConfiguration().collect {
                val screenState = TVShowsConfigurationState.SuccessConfiguration(it as Configuration)
                screenStateQueue.value =  screenState
            }
        }
    }
}