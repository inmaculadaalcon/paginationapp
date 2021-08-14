package com.inmaculadaalcon.fleksy_test.data.repository

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import com.inmaculadaalcon.fleksy_test.data.datasource.RemoteMovieDBDatasources
import kotlinx.coroutines.flow.Flow

class TVShowsRepositoryImpl(private val remoteMovieDBDatasources: RemoteMovieDBDatasources): TVShowsRepository {
    override fun getTopRatedTVShows(): Flow<PagingData<TopRatedTvShowDto>> {
        remoteMovieDBDatasources.getTopRatedTVShows().map{

        }
    }
}