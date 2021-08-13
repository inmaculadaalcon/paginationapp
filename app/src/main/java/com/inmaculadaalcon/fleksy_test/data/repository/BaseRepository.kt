package com.inmaculadaalcon.fleksy_test.data.repository

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.State.EmptyData
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

interface BaseRepository<E: DomainError, T> {

  fun asFlow(loading: Boolean = true) = flow<Either<E,T>> {
    val datasources = mutableListOf<Datasource>()
    datasources.forEach { ds ->
      when(ds) {
        is Datasource.Network -> fetchFromRemote()
      }
    }
  }

  suspend fun fetchFromRemote() : Unit

  private sealed class Datasource {
    object Network : Datasource()
  }
}
