package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.inmaculadaalcon.fleksy_test.data.datasource.TVShowsDatasources
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TVShowsRepositoryImpl(private val tvShowsDatasources: TVShowsDatasources ): TVShowsRepository {

    override fun getTopRatedTVShows(): Flow<PagingData<TVShow>> {
        return tvShowsDatasources.getTopRatedTVShows().map {
            pagingData ->  pagingData.map {
                tvShowDto -> tvShowDto.toDomain()
             }
        }
    }
}