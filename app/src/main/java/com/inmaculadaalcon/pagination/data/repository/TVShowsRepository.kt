package com.inmaculadaalcon.pagination.data.repository

import androidx.paging.PagingData
import com.inmaculadaalcon.pagination.domain.model.Configuration
import com.inmaculadaalcon.pagination.domain.model.DetailTVShow
import com.inmaculadaalcon.pagination.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.pagination.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
  fun getTopRatedTVShows(): Flow<PagingData<TVShow>>

  suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShow>

  fun getSimilarTVShows(tvShowId: Int, tvShow: TVShow): Flow<PagingData<TVShow>>

  suspend fun getConfiguration(): Flow<Configuration>
}
