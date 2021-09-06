package com.inmaculadaalcon.pagination.data.api.error

abstract class ServerApiException : Exception()

object ServerConnectionApiException : com.inmaculadaalcon.pagination.data.api.error.ServerApiException()

object ServerUnknownApiException : com.inmaculadaalcon.pagination.data.api.error.ServerApiException()

object Server500ApiException : com.inmaculadaalcon.pagination.data.api.error.ServerApiException()

object Server404ApiException : com.inmaculadaalcon.pagination.data.api.error.ServerApiException()

object Server403ApiException : com.inmaculadaalcon.pagination.data.api.error.ServerApiException()
