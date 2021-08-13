package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.*
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import com.inmaculadaalcon.fleksy_test.ui.base.ScreenState
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val getTopRatedTv: GetTopRatedTv): ViewModel() {

  val screenStateQueue = MutableStateFlow<TopRatedTVShowsStateScreen.SuccessTopRatedTVShows?>(null)
  val screenState = screenStateQueue.asStateFlow()

  fun getTopRatedTV(page: Int) {
    viewModelScope.launch {
      getTopRatedTv.getTopRatedTVShows(page).collect {
        println("RUINA Recoge algo???? -> $it")
        it.fold(
          {handleError(it)},
          {handleState(it)}
        )
        }
      }
    }

  suspend fun <T>handleState(it:T) {
    val topRatedTVShowsStateScreen = TopRatedTVShowsStateScreen.SuccessTopRatedTVShows(it as TopRatedTVShow)
    screenStateQueue.value = topRatedTVShowsStateScreen
  }

  suspend fun handleError(it: DomainError) {

  }
}
