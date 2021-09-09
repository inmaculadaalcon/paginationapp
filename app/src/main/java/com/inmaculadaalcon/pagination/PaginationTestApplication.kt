package com.inmaculadaalcon.pagination

import android.app.Application
import com.inmaculadaalcon.pagination.di.networkModule
import com.inmaculadaalcon.pagination.di.tvShowsModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class PaginationTestApplication: Application() {

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
