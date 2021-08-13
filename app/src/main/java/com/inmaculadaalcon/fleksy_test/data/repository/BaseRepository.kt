package com.inmaculadaalcon.fleksy_test.data.repository

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.State.EmptyData
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

interface BaseRepository<T> {

  fun asFlow() = flow<Either<DomainError, TopRatedTVShow>> {
    val datasources = mutableListOf<Datasource>()
    datasources.add(Datasource.Network)
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
