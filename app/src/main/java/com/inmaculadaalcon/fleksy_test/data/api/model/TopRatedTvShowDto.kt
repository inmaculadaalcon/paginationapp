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
data class TVShowDto(
  @Json(name = "backdrop_path") open val backdropPath: String?,
  @Json(name = "first_air_date") open val firstAirDate: String?,
  @Json(name = "genre_ids") open val genreIds: List<Int>?,
  @Json(name = "id") open val id: Int,
  @Json(name = "name") open val name: String,
  @Json(name = "origin_country") open val originCountry: List<String>?,
  @Json(name = "original_language") open val originalLanguage: String?,
  @Json(name = "original_name") open val originalName: String?,
  @Json(name = "overview") open val overview: String?,
  @Json(name = "popularity") open val popularity: Double,
  @Json(name = "poster_path") open val posterPath: String?,
  @Json(name = "vote_average") open val voteAverage: Double?,
  @Json(name = "vote_count") open val voteCount: Int?
)

@JsonClass(generateAdapter = true)
data class DetailTVShowDto(
  @Json(name = "backdrop_path")val backdropPath: String?,
  @Json(name = "created_by")val createdBy: List<CreatedByDto>,
  @Json(name = "episode_run_time")val episodeRunTime: List<Int>,
  @Json(name = "first_air_date")val firstAirDate: String,
  @Json(name = "genres")val genres: List<GenreDto>,
  @Json(name = "homepage")val homepage: String,
  @Json(name = "id")val id: Int,
  @Json(name = "in_production")val inProduction: Boolean,
  @Json(name = "languages")val languages: List<String>,
  @Json(name = "last_air_date")val lastAirDate: String,
  @Json(name = "last_episode_to_air")val lastEpisodeToAir: LastEpisodeToAirDto,
  @Json(name = "name")val name: String,
  @Json(name = "networks")val networks: List<NetworkDto>,
  @Json(name = "number_of_episodes")val numberOfEpisodes: Int,
  @Json(name = "number_of_seasons")val numberOfSeasons: Int,
  @Json(name = "origin_country")val originCountry: List<String>,
  @Json(name = "original_language")val originalLanguage: String,
  @Json(name = "original_name")val originalName: String?,
  @Json(name = "overview")val overview: String,
  @Json(name = "popularity")val popularity: Double,
  @Json(name = "poster_path")val posterPath: String? = null,
  @Json(name = "seasons")val seasons: List<SeasonDto>,
  @Json(name = "spoken_languages")val spokenLanguages: List<SpokenLanguageDto>,
  @Json(name = "status")val status: String,
  @Json(name = "type")val type: String,
  @Json(name = "tagline")val tagline: String,
  @Json(name = "vote_average")val voteAverage: Double?,
  @Json(name = "vote_count")val voteCount: Int?
)

data class SpokenLanguageDto(
  @Json(name = "english_name")val englishName: String,
  @Json(name = "iso_639_1")val iso6391: String,
  @Json(name = "name")val name: String
)

data class SeasonDto(
  @Json(name = "air_date")val airDate: String?,
  @Json(name = "episode_count")val episodeCount: Int,
  @Json(name = "id")val id: Int,
  @Json(name = "name")val name: String,
  @Json(name = "overview")val overview: String,
  @Json(name = "poster_path")val posterPath: String? = null,
  @Json(name = "season_number")val seasonNumber: Int
)

data class LastEpisodeToAirDto(
  @Json(name = "air_date")val airDate: String,
  @Json(name = "episode_number")val episodeNumber: Int,
  @Json(name = "id")val id: Int,
  @Json(name = "name")val name: String,
  @Json(name = "overview")val overView: String,
  @Json(name = "production_code")val productionCode: String,
  @Json(name = "season_number")val seasonNumber: Int,
  @Json(name = "still_path")val stillPath: String?,
  @Json(name = "vote_average")val voteAverage: Double?,
  @Json(name = "vote_count")val voteCount: Int?
)

data class NetworkDto(
  @Json(name = "name")val name: String,
  @Json(name = "id")val id: Int,
  @Json(name = "logo_path")val logoPath: String,
  @Json(name = "origin_country")val originCountry: String
)

data class CreatedByDto(
  @Json(name = "id")val id: Int,
  @Json(name = "credit_id")val creditId: String,
  @Json(name = "name")val name: String,
  @Json(name = "gender")val gender: Int?,
  @Json(name = "profile_path")val profilePath: String?
)

data class GenreDto(
  @Json(name = "id")val id: Int,
  @Json(name = "name")val name: String
)


@JsonClass(generateAdapter = true)
data class SimilarTVShowDto(
  @field:Json(name = "page") val page: Int,
  @field:Json(name = "results") val results: List<SimilarTVShowItemDto>,
  @field:Json(name = "total_pages") val totalPages: Int,
  @field:Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class SimilarTVShowItemDto(
  @field:Json(name = "adult") val adult: Boolean,
  @field:Json(name = "backdrop_path") val backdropPath: String?,
  @field:Json(name = "first_air_date") val firstAirDate: String?,
  @field:Json(name = "genre_ids") val genreIds: List<Int>?,
  @field:Json(name = "id") val id: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "origin_country") val originCountry: List<String>?,
  @field:Json(name = "original_language")val originalLanguage: String?,
  @field:Json(name = "original_name") open val originalName: String?,
  @field:Json(name = "overview") open val overview: String,
  @field:Json(name = "popularity") open val popularity: Double,
  @field:Json(name = "poster_path") open val posterPath: String?,
  @field:Json(name = "vote_average") open val voteAverage: Double?,
  @field:Json(name = "vote_count") open val voteCount: Int?
)
