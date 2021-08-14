package com.inmaculadaalcon.fleksy_test.data.api.client

import androidx.paging.PagingData
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.data.api.config.ServerApiClient
import com.inmaculadaalcon.fleksy_test.data.api.config.ServerApiConfig
import com.inmaculadaalcon.fleksy_test.data.api.model.SimilarTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import com.inmaculadaalcon.fleksy_test.domain.model.TVShow
import com.inmaculadaalcon.fleksy_test.ui.base.ResponseItems
import kotlinx.coroutines.flow.Flow

class MovieDBApiClient(serverApiConfig: ServerApiConfig.ServerApiTheMovieDBConfig): ServerApiClient(serverApiConfig) {
  var apiKey = BuildConfig.API_KEY
  companion object {
    const val DEFAULT_LANGUAGE = "en-US"
  }
  suspend fun getTopRatedTV(page: Int): ResponseItems<TopRatedTvShowDto> = getApi(MovieDBRest::class.java).getTopRatedTV(apiKey, DEFAULT_LANGUAGE, page)

  suspend fun getSimilarTV(tvShowId: Int, page: Int): SimilarTVShowDto = getApi(MovieDBRest::class.java).getSimilarTVShows(apiKey, tvShowId, DEFAULT_LANGUAGE, page)
}
