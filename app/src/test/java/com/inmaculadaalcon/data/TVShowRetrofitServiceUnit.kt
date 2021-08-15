package com.inmaculadaalcon.data

import com.inmaculadaalcon.common.FileReaderUtil
import com.inmaculadaalcon.fleksy_test.data.api.rest.MovieDBRest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
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
    private lateinit var movieService: MovieDBRest

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpMovieRetrofitService()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get movies remote response structure match JSON Server response`() = runBlocking {
        // This shouldn't have to throw an error if the MovieResponse
        // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
        val movies = movieService.getTopRatedTV(
            language = "en-US",
            page = 1
        )

        assertEquals(
            "Movies size does not match the one provided in resources.",
            TVShowData.provideRemoteTVShowsFromAssets().size,
            movies.results.size
        )
    }

    private fun setUpMovieRetrofitService() {
        movieService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MovieDBRest::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/movie/top_rated?language=en-US&page=1" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("movies.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}