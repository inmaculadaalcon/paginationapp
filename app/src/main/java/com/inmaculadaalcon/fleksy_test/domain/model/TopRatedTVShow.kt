package com.inmaculadaalcon.fleksy_test.domain.model

data class TopRatedTVShow (
  val page: Int,
  val results: List<TVShow>,
  val totalPages: Int,
  val totalResults: Int
)

open class TVShow(
  open val backdropPath: String?,
  open val firstAirDate: String?,
  open val genreIds: List<Int>?,
  open val id: Int,
  open val name: String,
  open val originCountry: List<String>?,
  open val originalLanguage: String?,
  open val originalName: String?,
  open val overview: String?,
  open val popularity: Double,
  open val posterPath: String?,
  open val voteAverage: Double?,
  open val voteCount: Int?,
  open val backgroundColor: Int? = null
)

data class SimilarTVShow(
  val page: Int,
  val results: List<SimilarTVShowItem>,
  val totalPages: Int,
  val totalResults: Int
)

data class SimilarTVShowItem(
  val adult: Boolean,
  override val backdropPath: String?,
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
  override val voteAverage: Double,
  override val voteCount: Int
): TVShow(backdropPath, firstAirDate,genreIds,id,name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount)
