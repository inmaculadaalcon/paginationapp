package com.inmaculadaalcon.fleksy_test.domain.usecase

import arrow.core.Either
import com.inmaculadaalcon.fleksy_test.data.repository.MovieDBRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import kotlinx.coroutines.flow.Flow

class GetTopRatedTv(private val repository: MovieDBRepository) {

  suspend fun getTopRatedTVShows(page: Int): Flow<Either<DomainError, TopRatedTVShow>> = repository.getTopRatedTVShows(page)
}
