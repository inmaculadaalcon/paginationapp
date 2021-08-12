package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import com.inmaculadaalcon.fleksy_test.ui.base.BaseScreenStateMenuViewModelStateFlow
import com.inmaculadaalcon.fleksy_test.ui.base.ScreenState
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(private val getTopRatedTv: GetTopRatedTv): BaseScreenStateMenuViewModelStateFlow<TopRatedTVShowsStateScreen>() {

  override val screenStateQueue = MutableStateFlow<ScreenState<TopRatedTVShowsStateScreen>>(ScreenState.Loading)
  override val screenState = screenStateQueue.asStateFlow()

  private var jobGetTopRatedTv: Job? = null

  fun getTopRatedTV(page: Int) = getTopRatedTv(page)


  private fun getTopRatedTv(page: Int) {
    println("RUINA GetTopRatedTv inside viewmodel")
    //cancelAll()
    viewModelScope.launch {
      delay(1000)
      getTopRatedTv.getTopRatedTVShows(page).collect {
        result ->
        println("RUINA Inside the launch -> $result")
        result.fold(
          {handleError(it)},
          {handleState(it)}
        )
      }
    }
  }

  override suspend fun <T> handleState(state: State<T>) {

    println("RUINA -> handleState: $state")
    when(state) {
      is State.Success -> {
        when(state.data) {
          is TopRatedTVShow -> {
            screenStateQueue.value = ScreenState.Render(TopRatedTVShowsStateScreen.SuccessTopRatedTVShows(state.data))
          }
        }
      }
      is State.EmptyData -> {
        screenStateQueue.value = (ScreenState.EmptyData)
      }
      is State.Loading -> {
        screenStateQueue.value = (ScreenState.Loading)
      }
    }
  }

  private fun cancelAll() {
    jobGetTopRatedTv?.cancel()
  }
  private fun handleError(state: StateError<DomainError>) {
    when(state) {
      is StateError.Error -> screenStateQueue.value =  ScreenState.Error(TopRatedTVShowsStateScreen.SomeError(state.error))
    }
  }
}
