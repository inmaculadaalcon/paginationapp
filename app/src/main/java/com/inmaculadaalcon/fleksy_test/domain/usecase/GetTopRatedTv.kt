package com.inmaculadaalcon.fleksy_test.domain.usecase

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.repository.MovieDBRepository
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

class GetTopRatedTv(private val repository: MovieDBRepository) {

  suspend fun getTopRatedTVShows(page: Int): Flow<PagingData<TVShow>> = repository.getTopRatedTVShows(page)
}
