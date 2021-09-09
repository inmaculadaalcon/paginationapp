package com.inmaculadaalcon.pagination.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inmaculadaalcon.pagination.data.api.model.ConfigurationDto
import com.inmaculadaalcon.pagination.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.data.api.rest.MovieDBRest
import com.inmaculadaalcon.pagination.data.api.rest.SimilarTVShowsPagingSource
import com.inmaculadaalcon.pagination.data.api.rest.TVShowsPagingSource
import com.inmaculadaalcon.pagination.domain.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class TVShowDataSourceImpl(private val movieDBRest: MovieDBRest): TVShowsDatasources {

    companion object {
        const val SERVICE_PAGE_SIZE = 20
    }
    override fun getTopRatedTVShows(): Flow<PagingData<TVShowDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = SERVICE_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TVShowsPagingSource(rest = movieDBRest)
            }
        ).flow
    }

    override suspend fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShowDto> {
       return flowOf<DetailTVShowDto>(
            movieDBRest.getDetailTVShow(tvShowId, language = "en-US"))
    }

    override fun getSimilarTVShows(showId: Int, tvShow: TVShow): Flow<PagingData<TVShowDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = SERVICE_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SimilarTVShowsPagingSource(rest = movieDBRest, showId, tvShow = tvShow)
            }
        ).flow
    }

    override suspend fun getConfiguration(): Flow<ConfigurationDto> {
        return flowOf<ConfigurationDto>(
            movieDBRest.getConfiguration())
    }
}
