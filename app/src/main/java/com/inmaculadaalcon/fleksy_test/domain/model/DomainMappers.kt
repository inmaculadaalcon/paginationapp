package com.inmaculadaalcon.fleksy_test.domain.model

import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto

fun TVShowDto.toDomain(): TVShow =
  TVShow(backdropPath,firstAirDate, genreIds, id, name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount)

