package com.inmaculadaalcon.pagination.data.api.config

import okhttp3.OkHttpClient

class HttpClientFactory {

    private val httpClient by lazy {
        OkHttpClient()
    }

    fun create(): OkHttpClient.Builder {
        return httpClient.newBuilder()
    }
}