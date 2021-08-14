package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
  fun getTopRatedTVShows(): Flow<PagingData<TVShow>>

  suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShow>

  fun getSimilarTVShows(tvShowId: Int): Flow<PagingData<SimilarTVShowItem>>
}
