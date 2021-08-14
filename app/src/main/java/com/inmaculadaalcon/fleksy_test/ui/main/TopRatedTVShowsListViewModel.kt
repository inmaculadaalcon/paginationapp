package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepository
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TopRatedTVShowsListViewModel(private val tvShowsRepository: TVShowsRepository): ViewModel() {

    fun getTopRatedTVShows(): Flow<PagingData<TVShow>> {
        return tvShowsRepository.getTopRatedTVShows().map {
            pagingData ->
                pagingData.map {
                    it
                }
        }.cachedIn(viewModelScope)
    }
}