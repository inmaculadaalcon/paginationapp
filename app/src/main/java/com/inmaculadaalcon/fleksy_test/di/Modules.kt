package com.inmaculadaalcon.fleksy_test.di

import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.data.api.config.HttpClientFactory
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import com.inmaculadaalcon.fleksy_test.data.datasource.TVShowDataSourceImpl
import com.inmaculadaalcon.fleksy_test.data.datasource.TVShowsDatasources
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepository
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepositoryImpl
import com.inmaculadaalcon.fleksy_test.domain.ResponseError
import com.inmaculadaalcon.fleksy_test.ui.detail.DetailTVShowViewModel
import com.inmaculadaalcon.fleksy_test.ui.main.TopRatedTVShowsListViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { HttpClientFactory() }
    single { provideOkHttpBuilder(httpClientFactory = get()) }
    single { provideClient(clientBuilder = get(), apiKey = BuildConfig.API_KEY) }
    single { provideRetrofitBuilder(okHttpClient = get()) }
    single { provideMoshi() }
    single { provideJsonErrorAdapter(moshi = get()) }
}

val tvShowsModule = module {
    factory { provideTVShowService(retrofit = get()) }
    factory<TVShowsDatasources> { TVShowDataSourceImpl(movieDBRest = get()) }
    factory<TVShowsRepository> {TVShowsRepositoryImpl(tvShowsDatasources = get())}
    viewModel { TopRatedTVShowsListViewModel(tvShowsRepository = get()) }
    viewModel { DetailTVShowViewModel(tvShowsRepository = get())}
}


internal fun provideTVShowService(retrofit: Retrofit): MovieDBRest =  retrofit.create(MovieDBRest::class.java)

internal fun provideOkHttpBuilder(
    httpClientFactory: HttpClientFactory
): OkHttpClient.Builder {
    return httpClientFactory.create()
}

fun provideRetrofitBuilder(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()
}

fun provideJsonErrorAdapter(moshi: Moshi): JsonAdapter<ResponseError> {
    return moshi.adapter(ResponseError::class.java)
}

internal fun provideMoshi(): Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

internal fun provideClient(
    clientBuilder: OkHttpClient.Builder,
    apiKey: String
): OkHttpClient {
    clientBuilder
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
    }
    return clientBuilder.build()
}