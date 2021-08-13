package com.inmaculadaalcon.fleksy_test.data.datasource

import arrow.core.Either
import arrow.core.invalid
import com.inmaculadaalcon.fleksy_test.data.api.client.MovieDBApiClient
import com.inmaculadaalcon.fleksy_test.data.api.extensions.apiException
import com.inmaculadaalcon.fleksy_test.data.api.extensions.mapResponse
import com.inmaculadaalcon.fleksy_test.domain.model.DomainError
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteMovieDBDatasources(private val apiClient: MovieDBApiClient) {

  suspend fun getTopRatedTVShows(page: Int): Either<DomainError, TopRatedTVShow> =
    try {
       mapResponse(apiClient.getTopRatedTV(page)) {
                    topRatedTvShowDto ->
                topRatedTvShowDto.toDomain()
            }
    }catch (exception: Exception) {
      Either.left(exception.apiException())
    }


  suspend fun getSimilarTVShows(tvShowId: Int, page: Int) =
    try {
        mapResponse(apiClient.getSimilarTV(tvShowId, page)) {
          similarTVShowDto ->
            similarTVShowDto.toDomain()
        }
    }catch (exception: Exception) {
      Either.left(exception.apiException())
    }
}
