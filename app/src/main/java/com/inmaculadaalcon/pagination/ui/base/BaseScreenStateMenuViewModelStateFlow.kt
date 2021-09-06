package com.inmaculadaalcon.pagination.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inmaculadaalcon.pagination.common.QueueLiveData
import com.inmaculadaalcon.pagination.domain.states.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseScreenStateViewModel<T : BaseScreenState> : ViewModel() {
  abstract val screenStateQueue: com.inmaculadaalcon.pagination.common.QueueLiveData<ScreenState<T>>
  abstract val screenState: LiveData<ScreenState<T>>
}

abstract class BaseScreenStateViewModelStateFlow<T : BaseScreenState> : ViewModel() {
  abstract val screenStateQueue: MutableStateFlow<ScreenState<T>>
  abstract val screenState: StateFlow<ScreenState<T>>
}

abstract class BaseScreenStateMenuViewModelStateFlow<T : BaseScreenState> : BaseScreenStateViewModelStateFlow<T>() {
  protected abstract suspend fun <T> handleState(
    state: State<T>
  )
}
