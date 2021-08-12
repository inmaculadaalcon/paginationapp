package com.inmaculadaalcon.fleksy_test.data.repository

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.data.datasource.RemoteMovieDBDatasources
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieDBRepository(private val remote: RemoteMovieDBDatasources) {

  fun getTopRatedTVShows(page: Int): Flow<Either<StateError<DomainError>, State<TopRatedTVShow>>> =
    object : BaseRepository<DomainError, TopRatedTVShow> {
      override suspend fun fetchFromRemote() {
        println("RUINA fetchFromRemote ->")
          remote.getTopRatedTVShows(page)
      }

      override fun fetchFromLocal(): Flow<Either<DomainError, TopRatedTVShow>> {
        TODO("Not yet implemented")
        //This would be able if the requirements changed
      }
    }.asFlow().flowOn(Dispatchers.IO)

  fun getSimilarTVShows(idTVShow: Int, page: Int): Flow<Either<StateError<DomainError>, State<SimilarTVShow>>> =
    object : BaseRepository<DomainError, SimilarTVShow> {

      override suspend fun fetchFromRemote() {
        remote.getSimilarTVShows(idTVShow, page)
      }

      override fun fetchFromLocal(): Flow<Either<DomainError, SimilarTVShow>> {
        TODO("Not yet implemented")
      }

    } .asFlow().flowOn(Dispatchers.IO)
}
