package com.inmaculadaalcon.fleksy_test.domain.usecase

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.data.repository.MovieDBRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.flow.Flow

class GetSimilarTVShows(private val repository: MovieDBRepository) {

  fun getSimilarTVShows(idTvShow: Int, page: Int): Flow<Either<DomainError, SimilarTVShow>> =
    repository.getSimilarTVShows(idTvShow, page)

}
