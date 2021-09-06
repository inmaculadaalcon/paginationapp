package com.inmaculadaalcon.pagination.data.datasource

import androidx.paging.PagingData
import com.inmaculadaalcon.pagination.data.api.model.ConfigurationDto
import com.inmaculadaalcon.pagination.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.pagination.data.api.model.SimilarTVShowItemDto
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.pagination.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsDatasources {

    fun getTopRatedTVShows(): Flow<PagingData<TVShowDto>>

    suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShowDto>

    fun getSimilarTVShows(tvShowId: Int, tvShow: TVShow): Flow<PagingData<TVShowDto>>

    suspend fun getConfiguration(): Flow<ConfigurationDto>
}
