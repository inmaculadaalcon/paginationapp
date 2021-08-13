package com.inmaculadaalcon.fleksy_test.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopRatedTvShowDto(
  @Json(name = "page") val page: Int,
  @Json(name = "results") val results: List<TVShowDto>,
  @Json(name = "total_pages") val totalPages: Int,
  @Json(name = "total_results") val totalResults: Int
)
@JsonClass(generateAdapter = true)
open class TVShowDto(
  @Json(name = "backdrop_path") open val backdropPath: String,
  @Json(name = "first_air_date") open val firstAirDate: String,
  @Json(name = "genre_ids") open val genreIds: List<Int>,
  @Json(name = "id") open val id: Int,
  @Json(name = "name") open val name: String,
  @Json(name = "origin_country") open val originCountry: List<String>,
  @Json(name = "original_language") open val originalLanguage: String,
  @Json(name = "original_name") open val originalName: String,
  @Json(name = "overview") open val overview: String,
  @Json(name = "popularity") open val popularity: Double,
  @Json(name = "poster_path") open val posterPath: String,
  @Json(name = "vote_average") open val voteAverage: Int,
  @Json(name = "vote_count") open val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class SimilarTVShowDto(
  @Json(name = "page") val page: Int,
  @Json(name = "results") val results: List<SimilarTVShowItemDto>,
  @Json(name = "total_pages") val totalPages: Int,
  @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class SimilarTVShowItemDto(
  @Json(name = "adult") val adult: Boolean,
  override val backdropPath: String,
  override val firstAirDate: String,
  override val genreIds: List<Int>,
  override val id: Int,
  override val name: String,
  override val originCountry: List<String>,
  override val originalLanguage: String,
  override val originalName: String,
  override val overview: String,
  override val popularity: Double,
  override val posterPath: String,
  override val voteAverage: Int,
  override val voteCount: Int
): TVShowDto(
  backdropPath,
  firstAirDate,
  genreIds,
  id,
  name,
  originCountry,
  originalLanguage,
  originalName,
  overview,
  popularity,
  posterPath,
  voteAverage,
  voteCount
)
