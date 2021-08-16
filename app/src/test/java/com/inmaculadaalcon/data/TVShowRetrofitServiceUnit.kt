package com.inmaculadaalcon.data

import com.inmaculadaalcon.common.FileReaderUtil
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import com.inmaculadaalcon.fleksy_test.di.provideMoshi
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TVShowRetrofitServiceUnit {

    //This will test if our data classes are well mapped with the expected response.
    private val mockWebServer = MockWebServer()
    private lateinit var movieDBRest: MovieDBRest

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpTVShowsRetrofitService()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get tvshows remote response structure match JSON Server response`() = runBlocking {
        // This shouldn't have to throw an error if the TVShowDto
        // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
        val response = movieDBRest.getTopRatedTV( language = "en-US", page = 1)

        assertEquals(
            "TVShows size does not match the one provided in resources.",
            TVShowData.provideRemoteTVShowsFromAssets().size,
            response.results.size
        )
    }

    private fun setUpTVShowsRetrofitService() {

        movieDBRest = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(MovieDBRest::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/tv/top_rated?api_key=${BuildConfig.API_KEY}&language=en-US&page=1" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("tvshows.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}