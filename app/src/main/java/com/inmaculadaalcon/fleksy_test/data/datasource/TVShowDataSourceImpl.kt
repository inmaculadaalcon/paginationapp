package com.inmaculadaalcon.fleksy_test.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import com.inmaculadaalcon.fleksy_test.data.api.rest.TVShowsPagingSource
import kotlinx.coroutines.flow.Flow

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
}