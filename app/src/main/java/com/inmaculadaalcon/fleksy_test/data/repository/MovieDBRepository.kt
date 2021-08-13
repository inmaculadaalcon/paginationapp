package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.compose.runtime.MutableState
import arrow.core.Either
import arrow.core.Left
import com.inmaculadaalcon.fleksy_test.data.datasource.RemoteMovieDBDatasources
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.State.Loading
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import com.inmaculadaalcon.fleksy_test.ui.base.ScreenState
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieDBRepository(private val remote: RemoteMovieDBDatasources) {

  suspend fun getTopRatedTVShows(page: Int): Flow<Either<DomainError, TopRatedTVShow>> {
    return flow{
      emit(remote.getTopRatedTVShows(page))
    }
  }
}

 /* fun getSimilarTVShows(idTVShow: Int, page: Int): Flow<Either<DomainError, SimilarTVShow>> =
    object : BaseRepository<Either<DomainError, SimilarTVShow>> {

      override suspend fun fetchFromRemote() {
        return remote.getSimilarTVShows(idTVShow, page)
      }

    } .asFlow().flowOn(Dispatchers.IO)
}*/
