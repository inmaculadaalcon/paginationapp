package com.inmaculadaalcon.fleksy_test.ui.base

import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow

sealed class BaseScreenState

sealed class ScreenState<out T : BaseScreenState> {
  object Loading : ScreenState<Nothing>()
  object EmptyData : ScreenState<Nothing>()
  class Error<out T : BaseScreenState>(val errorState: T) : ScreenState<T>()
  class Render<out T : BaseScreenState>(val renderState: T) : ScreenState<T>()
}

sealed class TopRatedTVShowsStateScreen : BaseScreenState() {
  class SuccessTopRatedTVShows(val data: TopRatedTVShow): TopRatedTVShowsStateScreen()
  class SomeError(val data: DomainError): TopRatedTVShowsStateScreen()
}