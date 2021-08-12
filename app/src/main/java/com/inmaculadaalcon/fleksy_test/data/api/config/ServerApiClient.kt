package com.inmaculadaalcon.fleksy_test.data.api.config

class ServerApiClient(private val serverApiConfig: ServerApiConfig) {

  fun <T> getApi(apiRest: Class<T>): T =
    serverApiConfig.retrofit.create(apiRest)
}
