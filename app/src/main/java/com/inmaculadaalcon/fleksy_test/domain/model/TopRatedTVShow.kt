package com.inmaculadaalcon.fleksy_test.domain.model

import com.inmaculadaalcon.fleksy_test.R

@kotlinx.serialization.Serializable
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
  open val backgroundColor: Int
)

data class SimilarTVShowItem(
  val adult: Boolean,
  override val backdropPath: String?,
  override val firstAirDate: String?,
  override val genreIds: List<Int>?,
  override val id: Int,
  override val name: String,
  override val originCountry: List<String>?,
  override val originalLanguage: String?,
  override val originalName: String?,
  override val overview: String?,
  override val popularity: Double,
  override val posterPath: String?,
  override val voteAverage: Double?,
  override val voteCount: Int?
): TVShow(backdropPath, firstAirDate,genreIds,id,name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount, backgroundColor = R.drawable.background_blue)

data class DetailTVShow(
  val backdropPath: String?,
  val createdBy: List<CreatedBy>,
  val episodeRunTime: List<Int>,
  val firstAirDate: String,
  val genres: List<Genre>,
  val homepage: String,
  val id: Int,
  val inProduction: Boolean,
  val languages: List<String>,
  val lastAirDate: String,
  val lastEpisodeToAir: LastEpisodeToAir,
  val name: String,
  val networks: List<Network>,
  val numberOfEpisodes: Int,
  val numberOfSeasons: Int,
  val originCountry: List<String>?,
  val originalLanguage: String?,
  val originalName: String?,
  val overview: String,
  val popularity: Double,
  val posterPath: String?,
  val seasons: List<Season>,
  val spokenLanguages: List<SpokenLanguage>,
  val status: String,
  val type: String,
  val tagline: String,
  val voteAverage: Double?,
  val voteCount: Int?
)

data class SpokenLanguage(
  val englishName: String,
  val iso6391: String,
  val name: String
)

data class Season(
  val airDate: String?,
  val episodeCount: Int,
  val id: Int,
  val name: String,
  val overview: String,
  val posterPath: String?,
  val seasonNumber: Int
)

data class LastEpisodeToAir(
  val airDate: String,
  val episodeNumber: Int,
  val id: Int,
  val name: String,
  val overView: String,
  val productionCode: String,
  val seasonNumber: Int,
  val stillPath: String?,
  val voteAverage: Double?,
  val voteCount: Int?
)

data class Network(
  val name: String,
  val id: Int,
  val logoPath: String,
  val originCountry: String
)

data class CreatedBy(
  val id: Int,
  val creditId: String,
  val name: String,
  val gender: Int?,
  val profilePath: String?
)

data class Genre(
  val id: Int,
  val name: String
)