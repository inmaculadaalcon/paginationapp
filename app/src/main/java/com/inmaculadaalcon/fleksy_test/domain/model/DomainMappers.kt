package com.inmaculadaalcon.fleksy_test.domain.model

import com.inmaculadaalcon.fleksy_test.data.api.model.SimilarTVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.SimilarTVShowItemDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.api.model.TopRatedTvShowDto

fun TopRatedTvShowDto.toDomain(): TopRatedTVShow {
  val newResults: MutableList<TVShow> = mutableListOf()
  results.forEach{
    newResults.add(it.toDomain())
  }
  return TopRatedTVShow(page, newResults.toList(), totalPages, totalResults)
}

fun TVShowDto.toDomain(): TVShow =
  TVShow(backdropPath,firstAirDate, genreIds, id, name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount)

fun SimilarTVShowDto.toDomain(): SimilarTVShow {
  val newResults: MutableList<SimilarTVShowItem> = mutableListOf()
  results.forEach{
    newResults.add(it.toDomain())
  }
  return SimilarTVShow( page, newResults.toList(), totalPages, totalResults)
}

fun SimilarTVShowItemDto.toDomain(): SimilarTVShowItem {
  return SimilarTVShowItem(adult,backdropPath,firstAirDate,genreIds,id,name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount)
}
