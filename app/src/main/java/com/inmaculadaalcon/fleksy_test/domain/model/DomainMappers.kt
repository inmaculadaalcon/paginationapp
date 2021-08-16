package com.inmaculadaalcon.fleksy_test.domain.model

import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.data.api.model.*
import com.squareup.moshi.Json
import java.util.*

var backgrounds = listOf(R.drawable.background, R.drawable.background_blue, R.drawable.background_pink, R.drawable.background_purple)


fun TVShowDto.toDomain(): TVShow {
  val background = backgrounds[Random().nextInt(backgrounds.size)]
  return TVShow(backdropPath,firstAirDate, genreIds, id, name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount, background)
}

fun DetailTVShowDto.toDomain(): DetailTVShow {
  return DetailTVShow(backdropPath, createdBy.map { createdByDto -> createdByDto.toDomain()}, episodeRunTime, firstAirDate, genres.map{ genreDto -> genreDto.toDomain()}, homepage, id, inProduction, languages, lastAirDate, lastEpisodeToAir.toDomain(), name, networks.map {networkDto -> networkDto.toDomain()}, numberOfEpisodes, numberOfSeasons, originCountry, originalLanguage, originalName, overview,
  popularity, posterPath, seasons.map {seasonDto ->  seasonDto.toDomain()}, spokenLanguages.map{spokenLanguageDto -> spokenLanguageDto.toDomain()}, status, type, tagline, voteAverage, voteCount)
}

fun SimilarTVShowItemDto.toDomain(): SimilarTVShowItem {
  return SimilarTVShowItem(adult, backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount)
}

fun TVShow.toDto(): TVShowDto{
  return TVShowDto(backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount)
}

fun SpokenLanguageDto.toDomain(): SpokenLanguage {
  return SpokenLanguage(englishName, iso6391, name)
}

fun SeasonDto.toDomain():  Season {
  return Season(airDate, episodeCount, id, name, overview, posterPath, seasonNumber)
}

fun NetworkDto.toDomain(): Network {
  return Network(name, id, logoPath, originCountry)
}

fun LastEpisodeToAirDto.toDomain(): LastEpisodeToAir {
  return LastEpisodeToAir(airDate, episodeNumber, id, name, overView, productionCode, seasonNumber, stillPath, voteAverage, voteCount)
}

fun CreatedByDto.toDomain(): CreatedBy {
  return CreatedBy(id, creditId, name, gender, profilePath)
}

fun GenreDto.toDomain(): Genre {
  return Genre(id, name)
}