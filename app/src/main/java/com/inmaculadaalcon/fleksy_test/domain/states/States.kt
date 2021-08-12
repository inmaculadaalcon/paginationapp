package com.inmaculadaalcon.fleksy_test.domain.states

import com.inmaculadaalcon.fleksy_test.domain.model.DomainError

sealed class State<T> {
  class Loading<T>: State<T>()
  class EmptyData<T>: State<T>()
  data class Success<T>(val data: T): State<T>()
}

sealed class StateError<T: DomainError> {
  data class Error<T: DomainError>(val error: DomainError): StateError<T>()
}
