package com.inmaculadaalcon.fleksy_test.data.api.rest

import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import com.inmaculadaalcon.fleksy_test.domain.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBRest {

  /**
   * @return list of [TVShowDto]
   *
   * @param api_key from [https://developers.themoviedb.org/]
   * @param language
   * @param page the page we are getting the data
   * */

  @GET("tv/top_rated")
  suspend fun getTopRatedTV(@Query("language") language: String,
                            @Query("page") page: Int
  ): ResponseItems<TVShowDto>

  /**
   * @return list of [SimilarTVShowItemDto]
   *
   * @param api_key from [https://developers.themoviedb.org/]
   * @param language
   * @param page the page we are getting the data
   * */

  /*@GET("${BuildConfig.BASE_URL}tv/{tv_id}/similar")
  suspend fun getSimilarTVShows(@Query("api_key")apiKey: String,
                                @Path("tv_id")tvId: Int,
                                @Query("language")language: String,
                                @Path("page")page:Int = 1): ResponseItems<SimilarTVShowItemDto>*/
}
