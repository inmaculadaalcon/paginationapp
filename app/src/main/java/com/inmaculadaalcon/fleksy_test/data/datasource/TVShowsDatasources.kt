package com.inmaculadaalcon.fleksy_test.data.datasource

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.SimilarTVShowItemDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsDatasources {

    fun getTopRatedTVShows(): Flow<PagingData<TVShowDto>>

    suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShowDto>

    fun getSimilarTVShows(tvShowId: Int, tvShow: TVShow): Flow<PagingData<TVShowDto>>
}
