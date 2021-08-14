package com.inmaculadaalcon.fleksy_test.domain

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsRepository {

    fun getTopRatedTVShows(): Flow<PagingData<TVShow>>
}