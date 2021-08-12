package com.inmaculadaalcon.fleksy_test.domain.usecase

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.data.repository.MovieDBRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.states.State
import com.inmaculadaalcon.fleksy_test.domain.states.StateError
import kotlinx.coroutines.flow.Flow

class GetTopRatedTv(private val repository: MovieDBRepository) {

  fun getTopRatedTVShows(page: Int): Flow<Either<StateError<DomainError>, State<TopRatedTVShow>>> =
    repository.getTopRatedTVShows(page)


}
