package com.inmaculadaalcon.fleksy_test.data.api.config

import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

open class ServerApiConfig(private val baseUrl: String, private val moshiConverter: Moshi? = null) {

  var retrofit: Retrofit = initialize()

  companion object {
    private const val TIME_OUT_SECONDS: Long = 60
  }

  private fun initialize(): Retrofit {
    lateinit var retrofit: Retrofit
    val moshiConverterFactory = moshiConverter?.let {
      MoshiConverterFactory.create(moshiConverter) }?: MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())

    retrofit = Retrofit.Builder().baseUrl(baseUrl).client(getOkHttpClient().build()).addConverterFactory(moshiConverterFactory).build()
    return retrofit
  }

  private fun getOkHttpClient(): OkHttpClient.Builder {
    val client = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()

    client.connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
    client.connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
      logging.level = HttpLoggingInterceptor.Level.BASIC
    } else {
      logging.level = HttpLoggingInterceptor.Level.NONE
    }
    client.addInterceptor(logging)

    return client
  }

  class ServerApiTheMovieDBConfig(baseUrl: String): ServerApiConfig(baseUrl)

  class ServerApiTheMovieDBConfigBuilder(private val baseUrl: String = BuildConfig.BASE_URL) {
    fun build(): ServerApiTheMovieDBConfig = ServerApiTheMovieDBConfig(baseUrl)
  }

}
