package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import com.inmaculadaalcon.fleksy_test.ui.base.BaseScreenStateViewModelStateFlow
import com.inmaculadaalcon.fleksy_test.ui.base.ScreenState
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(private val getTopRatedTv: GetTopRatedTv): BaseScreenStateViewModelStateFlow<TopRatedTVShowsStateScreen>() {

  private var _topRatedTVShow = MutableLiveData<Result<TopRatedTVShow>>()
  val topRatedTVShow: LiveData<Result<TopRatedTVShow>>
    get() = topRatedTVShow
  override val screenStateQueue = MutableStateFlow<ScreenState<TopRatedTVShowsStateScreen>>(ScreenState.Loading)
  override val screenState = screenStateQueue.asStateFlow()

  private var jobGetTopRatedTv: Job? = null

  fun getTopRatedTV(page: Int) = getTopRatedTv(page)


  private fun getTopRatedTv(page: Int) {
    cancelAll()
    jobGetTopRatedTv = viewModelScope.launch {
      getTopRatedTv.getTopRatedTVShows(page).collect {
        result ->
        result.fold(
          {handleError(it)},
          {handleState(it)}
        )
      }
    }
  }

  suspend fun <T> handleState(state: State<T>) {
    when(state) {
      is State.Success -> {
        when(state.data) {
          is TopRatedTVShow -> {
            screenStateQueue.value = ScreenState.Render(state.data as TopRatedTVShow)
          }
        }
      }
    }
  }

  private fun cancelAll() {
    jobGetTopRatedTv?.cancel()
  }
  private fun handleError(state: StateError<DomainError>) = Unit // Not implemented
}
