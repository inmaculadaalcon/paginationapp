package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {
  fun getTopRatedTVShows(): Flow<PagingData<TopRatedTvShowDto>>
}
