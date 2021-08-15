package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.inmaculadaalcon.fleksy_test.data.api.extensions.mapResponse
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.datasource.TVShowsDatasources
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.SimilarTVShowItem
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TVShowsRepositoryImpl(private val tvShowsDatasources: TVShowsDatasources ): TVShowsRepository {

    override fun getTopRatedTVShows(): Flow<PagingData<TVShow>> {
        return tvShowsDatasources.getTopRatedTVShows().map {
            pagingData ->  pagingData.map {
                tvShowDto -> tvShowDto.toDomain()
             }
        }
    }
    override suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShow> {
        return tvShowsDatasources.getDetailTVShow(tvShowId).map {
                detailTVShowDto ->
            detailTVShowDto.toDomain()
        }
    }

    override fun getSimilarTVShows(tvShowId: Int): Flow<PagingData<TVShow>> {
        return tvShowsDatasources.getSimilarTVShows(tvShowId).map {
            pagingData ->  pagingData.map {
               similarTVShowItem -> similarTVShowItem.toDomain()
            }
        }
    }


}