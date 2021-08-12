package com.inmaculadaalcon.fleksy_test.data.repository

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BaseRepository<E: DomainError, T> {

  fun asFlow(loading: Boolean = true) = flow<Either<StateError<E>, State<T>>> {
    if (loading) {
      emit(Either.right(State.Loading()))
    }
    val datasources = mutableListOf<Datasource>()
    datasources.forEach { ds ->
      when(ds) {
        is Datasource.Network -> fetchFromRemote()
        is Datasource.Local -> fetchFromLocal()
      }
    }
  }

  fun fetchFromLocal(): Flow<Either<DomainError, T>>

  suspend fun fetchFromRemote() = Unit

  private sealed class Datasource {
    object Local : Datasource()
    object Network : Datasource()
  }
}
