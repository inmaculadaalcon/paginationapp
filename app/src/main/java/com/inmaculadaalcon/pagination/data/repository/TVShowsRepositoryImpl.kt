package com.inmaculadaalcon.pagination.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.inmaculadaalcon.pagination.data.api.extensions.mapResponse
import com.inmaculadaalcon.pagination.data.api.model.ConfigurationDto
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.data.datasource.TVShowsDatasources
import com.inmaculadaalcon.pagination.domain.model.*
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

    override fun getSimilarTVShows(tvShowId: Int, tvShow: TVShow): Flow<PagingData<TVShow>> {
        return tvShowsDatasources.getSimilarTVShows(tvShowId, tvShow).map {
            pagingData ->  pagingData.map {
               similarTVShowItem -> similarTVShowItem.toDomain()
            }
        }
    }

    override suspend fun getConfiguration(): Flow<Configuration> {
        return tvShowsDatasources.getConfiguration().map {
            configurationDto ->
            configurationDto.toDomain()
        }
    }

}