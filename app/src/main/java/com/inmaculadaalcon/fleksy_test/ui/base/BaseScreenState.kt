package com.inmaculadaalcon.fleksy_test.ui.base

import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow

sealed class BaseScreenState

sealed class ScreenState<out T : BaseScreenState> {
  object Loading : ScreenState<Nothing>()
  object EmptyData : ScreenState<Nothing>()
  class Error<out T : BaseScreenState>(val errorState: T) : ScreenState<T>()
  class Render<out T : BaseScreenState>(val renderState: T) : ScreenState<T>()
}

sealed class TVShowDetailScreenState : BaseScreenState() {
  class SuccessTopRatedTVShows(val data: DetailTVShow): TVShowDetailScreenState()
}
