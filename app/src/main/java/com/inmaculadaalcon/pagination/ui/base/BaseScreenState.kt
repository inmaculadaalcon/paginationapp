package com.inmaculadaalcon.pagination.ui.base

import com.inmaculadaalcon.pagination.domain.model.Configuration
import com.inmaculadaalcon.pagination.domain.model.DetailTVShow

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

sealed class TVShowsConfigurationState: BaseScreenState() {
  class SuccessConfiguration(val configuratuin: Configuration): TVShowsConfigurationState()
}
