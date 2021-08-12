package com.inmaculadaalcon.fleksy_test.di

import com.inmaculadaalcon.fleksy_test.data.api.client.MovieDBApiClient
import com.inmaculadaalcon.fleksy_test.data.api.config.ServerApiConfig
import com.inmaculadaalcon.fleksy_test.data.datasource.RemoteMovieDBDatasources
import com.inmaculadaalcon.fleksy_test.data.repository.MovieDBRepository
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetSimilarTVShows
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import org.koin.dsl.module

val networkModule = module {
  single {
    MovieDBApiClient(get())
  }
  single {
    ServerApiConfig.ServerApiTheMovieDBConfigBuilder().build()
  }
}

val useCaseModule = module {
  single {
    GetTopRatedTv(get())
  }

  single {
    GetSimilarTVShows(get())
  }
}

val repositoryModule = module {
  single {
    MovieDBRepository(get())
  }
}

val dataSourceModule = module {
  single {
    RemoteMovieDBDatasources(get())
  }
}
