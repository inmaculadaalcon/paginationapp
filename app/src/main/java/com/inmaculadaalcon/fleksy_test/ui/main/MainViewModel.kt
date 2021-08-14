package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val getTopRatedTv: GetTopRatedTv): ViewModel() {

  fun getTopRatedTV(page: Int): Flow<PagingData<TVShow>> =
    getTopRatedTv.getTopRatedTVShows(page).map { pagingData ->
      pagingData.map {

      }
    }.cachedIn(viewModelScope)

}
