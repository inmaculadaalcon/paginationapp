package com.inmaculadaalcon.fleksy_test.data.datasource

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import kotlinx.coroutines.flow.Flow

interface TVShowsDatasources {
    fun getTopRatedTVShows(): Flow<PagingData<TVShowDto>>

    fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShowDto>
}
