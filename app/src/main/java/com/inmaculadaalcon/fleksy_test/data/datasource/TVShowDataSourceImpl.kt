package com.inmaculadaalcon.fleksy_test.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.extensions.mapResponse
import com.inmaculadaalcon.fleksy_test.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import com.inmaculadaalcon.fleksy_test.data.api.rest.TVShowsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShowDto> {
       return flow<DetailTVShowDto> {
            movieDBRest.getDetailTVShow(tvShowId, language = "en-US")
        }
    }


}