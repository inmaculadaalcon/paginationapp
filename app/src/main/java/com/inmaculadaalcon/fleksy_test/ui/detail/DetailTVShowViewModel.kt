package com.inmaculadaalcon.fleksy_test.ui.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
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

}