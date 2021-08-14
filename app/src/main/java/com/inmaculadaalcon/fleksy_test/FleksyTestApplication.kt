package com.inmaculadaalcon.fleksy_test

import android.app.Application
import com.inmaculadaalcon.fleksy_test.di.*
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class FleksyTestApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin{
      androidContext(applicationContext)
      modules(
        networkModule,
        tvShowsModule
      )
    }
  }
}
