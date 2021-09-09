package com.inmaculadaalcon.pagination.domain

import androidx.paging.PagingData
import com.inmaculadaalcon.pagination.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {

    fun getTopRatedTVShows(): Flow<PagingData<TVShow>>
}