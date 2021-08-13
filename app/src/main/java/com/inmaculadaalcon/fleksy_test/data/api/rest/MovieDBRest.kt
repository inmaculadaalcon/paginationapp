package com.inmaculadaalcon.fleksy_test.data.api.rest

import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.data.api.model.SimilarTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBRest {

  @GET("${BuildConfig.BASE_URL}tv/top_rated")
  suspend fun getTopRatedTV(@Query("api_key")apiKey: String, @Query("language")language: String = "en-US", @Query("page")page: Int = 1): TopRatedTvShowDto

  @GET("${BuildConfig.BASE_URL}tv/{tv_id}/similar?api_key={api_key}}&language={language}&page={page}")
  suspend fun getSimilarTVShows(@Path("api_key")apiKey: String, @Path("tv_id")tvId: Int, @Path("language")language: String, @Path("page")page:Int = 1): SimilarTVShowDto
}
