package com.inmaculadaalcon.pagination.data.api.rest

import com.inmaculadaalcon.pagination.data.api.model.ConfigurationDto
import com.inmaculadaalcon.pagination.data.api.model.DetailTVShowDto
import com.inmaculadaalcon.pagination.data.api.model.SimilarTVShowItemDto
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.domain.ResponseItems
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

  @GET("tv/{tv_id}/similar")
  suspend fun getSimilarTVShows(@Path("tv_id")tvId: Int,
                                @Query("language")language: String,
                                @Query("page")page:Int = 1): ResponseItems<TVShowDto>


  @GET("tv/{tv_id}")
  suspend fun getDetailTVShow(@Path("tv_id") tvShowId: Int, @Query("language") language: String): DetailTVShowDto


  @GET("configuration")
  suspend fun getConfiguration(): ConfigurationDto

}
