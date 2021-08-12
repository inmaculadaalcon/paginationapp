package com.inmaculadaalcon.fleksy_test.data.api.client

import com.inmaculadaalcon.fleksy_test.data.api.config.ServerApiClient
import com.inmaculadaalcon.fleksy_test.data.api.config.ServerApiConfig
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest

class MovieDBApiClient(serverApiConfig: ServerApiConfig.ServerApiTheMovieDBConfig): ServerApiClient(serverApiConfig) {
  var apiKey = ""
  companion object {
    const val DEFAULT_LANGUAGE = "en-US"
  }
  suspend fun getTopRatedTV(page: Int): TopRatedTvShowDto = getApi(MovieDBRest::class.java).getTopRatedTV(apiKey, DEFAULT_LANGUAGE, page)
}
