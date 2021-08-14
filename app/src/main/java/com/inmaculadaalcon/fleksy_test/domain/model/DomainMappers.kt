package com.inmaculadaalcon.fleksy_test.domain.model

import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import java.util.*

var backgrounds = listOf(R.drawable.background, R.drawable.background_blue, R.drawable.background_green, R.drawable.background_orange, R.drawable.background_pink, R.drawable.background_purple)


fun TVShowDto.toDomain(): TVShow {
  val background = backgrounds[Random().nextInt(backgrounds.size)]
  return TVShow(backdropPath,firstAirDate, genreIds, id, name,originCountry,originalLanguage,originalName,overview,popularity,posterPath,voteAverage,voteCount, background)
}

